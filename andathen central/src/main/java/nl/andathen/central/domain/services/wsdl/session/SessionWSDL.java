package nl.andathen.central.domain.services.wsdl.session;

import java.math.BigInteger;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import org.apache.commons.lang3.SerializationUtils;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;

import nl.andathen.central.dao.UserDao;
import nl.andathen.central.domain.person.User;
import nl.andathen.central.domain.person.User.Role;
import nl.andathen.central.util.session.CryptoUtil;
import nl.andathen.central.util.session.Session;
import nl.andathen.central.util.session.SessionManager;

@WebService(endpointInterface="services.SessionService")
public class SessionWSDL implements SessionService {
	@Inject
	private SessionManager sessionManager;
    @Inject
    private Pbkdf2PasswordHash passwordHash;
    @Inject
    private UserDao userDao;
	
	@Override
	public byte[] testSession(BigInteger rawSessionId, byte[] prompt) {
		try {
			byte[] result = sessionManager.testSession(rawSessionId, prompt);
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public byte[] startSession(byte[] serializedPublicBytes) {
		try {
			// Deserialize the public key
			RSAKeyParameters publicKey = (RSAKeyParameters) PublicKeyFactory.createKey(serializedPublicBytes);
			
			// Get a Session object from the Session Manager and return it
			return sessionManager.startSession(publicKey);

		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public byte[] createUser(BigInteger sessionId, byte[] encryptedUsername, byte[] encryptedPassword, byte[] encryptedFirstname, byte[] encryptedMiddlename, byte[] encryptedLastname, byte[] encryptedEmail,  byte[] encryptedRole) {
		try {
			Session session = sessionManager.getSession(sessionId);
			if (session == null) {
				return null;
			}
			
			// Decrypt parameters and convert to objects
			String username = SerializationUtils.deserialize(CryptoUtil.decryptSymmetric(session.getSessionKey(), session.getSalt(), encryptedUsername));
			String password = SerializationUtils.deserialize(CryptoUtil.decryptSymmetric(session.getSessionKey(), session.getSalt(), encryptedPassword));
			String firstName = SerializationUtils.deserialize(CryptoUtil.decryptSymmetric(session.getSessionKey(), session.getSalt(), encryptedFirstname));
			String middleName = SerializationUtils.deserialize(CryptoUtil.decryptSymmetric(session.getSessionKey(), session.getSalt(), encryptedMiddlename));
			String lastName = SerializationUtils.deserialize(CryptoUtil.decryptSymmetric(session.getSessionKey(), session.getSalt(), encryptedLastname));
			String email = SerializationUtils.deserialize(CryptoUtil.decryptSymmetric(session.getSessionKey(), session.getSalt(), encryptedEmail));
			Role role = SerializationUtils.deserialize(CryptoUtil.decryptSymmetric(session.getSessionKey(), session.getSalt(), encryptedRole));

			// Create user and store in database
			User user = sessionManager.createUser(sessionId, username, passwordHash.generate(password.toCharArray()), firstName, middleName, lastName, email,  role);
			byte[] answer = CryptoUtil.encryptSymmetric(session.getSessionKey(), session.getSalt(), SerializationUtils.serialize(user));
			userDao.create(user);
			return answer;
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean logon(BigInteger sessionId, byte[] username, byte[] password) {
		// TODO Auto-generated method stub
		return false; 
	}

	@Override
	public void logout(BigInteger sessionId, byte[] username, byte[] password) {
		// TODO Auto-generated method stub
	}
}