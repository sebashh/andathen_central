package nl.andathen.central.util.session;

import java.net.MalformedURLException;
import static nl.andathen.central.beans.ApplicationScopedBean.*;
import java.net.URL;
import java.security.SecureRandom;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.commons.lang3.SerializationUtils;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;

import nl.andathen.central.services.wsdl.session.SessionService;
import nl.andathen.central.util.security.CryptoUtil;

public class SessionClient {
	private SecureRandom random = null;
	private final SessionService sessionService;
	private Session session;
	
	public SessionClient() throws Exception {
		this(DEFAULT_SERVICE_URL);
	}
	
	public SessionClient(String serviceURL) throws Exception {
		if (serviceURL == null) {
			serviceURL = DEFAULT_SERVICE_URL;
		}
		sessionService = createSessionService(serviceURL);
		startSession();
	}
	
	private Session startSession() throws Exception {
		random = SecureRandom.getInstanceStrong();
		session =  createSession();
		while(!testSession()) {
			session = createSession();
		}
		return session;
	}
	
	public SessionService getSessionService() {
		return sessionService;
	}

	public Session getSession() {
		return session;
	}

	private SessionService createSessionService(String sessionURL) throws MalformedURLException {
		if (sessionURL == null) {
			sessionURL = DEFAULT_SERVICE_URL;
		}
		// Initialize the service
//		URL url = new URL(sessionURL + APPLICATION_NAME + "services/wsdl/session/SessionWSDL?wsdl");
//	    QName qname = new QName("http://session.wsdl.services.central.andathen.nl/", "SessionWSDLService");	

		URL url = new URL(DEFAULT_SERVICE_URL + "session/SessionWSDL?wsdl");
	    QName qname = new QName("http://session" + REVERSED_SERVICE_URL, "SessionWSDLService");
		
	    Service service = Service.create(url, qname);
	    SessionService ss = service.getPort(SessionService.class);
	    return ss;
	}
	
	public SessionService createSessionService() throws MalformedURLException {
		return createSessionService(DEFAULT_SERVICE_URL);
	}
	
	private Session createSession() throws Exception {	    
		// Generate key pair for the client
		AsymmetricCipherKeyPair clientKey = generateKeys();
		RSAKeyParameters userPublicKey = (RSAKeyParameters) clientKey.getPublic();
		
		// Serialize the user public key
		SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(userPublicKey);
		byte[] serializedPublicBytes = publicKeyInfo.toASN1Primitive().getEncoded();
		
		// Start the session
	    byte[] data = sessionService.startSession(serializedPublicBytes);
		Session session = (Session) SerializationUtils.deserialize(data);
		
		// Decrypt the session data with the private key and return the final object
		session.decryptKeys(clientKey.getPrivate());
		return session;
	}
	
	private boolean testSession() throws Exception {
		try {
			byte[] prompt = CryptoUtil.encryptSymmetric(session.getSessionKey(), session.getSalt(), "Knock knock".getBytes());
			byte[] answer = sessionService.testSession(session.getSessionId(), prompt);
			String result = new String(CryptoUtil.decryptSymmetric(session.getSessionKey(), session.getSalt(), answer));
			if (result.equals("Knock knock, Who's there?")) {
				return true;
			}
			else {
				return false;
			}
		}
		catch (IllegalArgumentException e) {
			return false;
		}
	}
	
    private AsymmetricCipherKeyPair generateKeys() throws Exception {
	    RSAKeyPairGenerator generator = new RSAKeyPairGenerator();
	    generator.init(new RSAKeyGenerationParameters(CryptoUtil.PUBLIC_EXPONENT,
	                					random,
	                					CryptoUtil.KEYSIZE,
	                					CryptoUtil.CERTAINTY));
	    return generator.generateKeyPair();
    }
}