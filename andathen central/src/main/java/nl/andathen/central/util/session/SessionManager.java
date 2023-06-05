package nl.andathen.central.util.session;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.lang3.SerializationUtils;
import org.bouncycastle.crypto.params.RSAKeyParameters;

import nl.andathen.central.domain.person.User;
import nl.andathen.central.domain.person.User.Role;
import nl.andathen.central.util.security.CryptoUtil;

@ApplicationScoped
public class SessionManager implements Runnable {
	public static final int TIMER = 60*1000; 				// Clean once a minute
	public static final long SESSION_TIMEOUT = 60*60*1000; 	// Timeout set to once one hour
	private SessionManager internalObject;
	private IterableMap<BigInteger, Session> sessions;
	
	public void init(@Observes @Initialized(ApplicationScoped.class) Object obj) {
		try {
			CryptoUtil.initBouncyCastle();
			sessions = new TreeBidiMap<>();
			Thread t = new Thread(internalObject);
			t.setDaemon(true);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(123);
		}
	}
	
	public synchronized byte[] startSession(RSAKeyParameters userPublicKey) throws Exception {
		Session newSession  = new Session();
		Session session  = newSession.clone();
		sessions.put(newSession.getSessionId(), newSession);
		session.encryptKeys(userPublicKey);
		return SerializationUtils.serialize(session);
	}
	
	public synchronized Session addSession(Session session) {
		return sessions.put(session.getSessionId(), session);
	}
	
	public synchronized Session removeSession(Session session) {
		return sessions.remove(session.getSessionId());
	}
	
	public synchronized Session getSession(BigInteger id) {
		return sessions.get(id);
	}
	
	public synchronized boolean contains(Session session) {
		return (sessions.containsValue(session));
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(TIMER);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(this) {
			MapIterator<BigInteger,Session> i = sessions.mapIterator();
			while (i.hasNext()) {
				i.next();
				Session session = i.getValue();
				LocalDateTime started = session.getLastAction();
				if (started == null) {
					started = LocalDateTime.now();
				}
		        Duration duration = Duration.between(session.getLastAction(), LocalDateTime.now());
		        if (duration.getSeconds() > SESSION_TIMEOUT) {
		        	session.setInvalid();
		        	i.remove();
		        }
			}
		}
	}
	
	// A test method!
	// Send the session Id
	// Send the prompt using the symmetric session key
	//
	public byte[] testSession(BigInteger rawSessionId, byte[] prompt) throws Exception {	
		return knockKnock(rawSessionId, prompt);
	}
	
	public byte[] knockKnock(BigInteger sessionId, byte[] prompt) throws Exception {	
		if ((sessionId == null) || (prompt == null)) {
			return null;
		}
		Session session = sessions.get(sessionId);
		if (session == null) {
			return null;
		}	
		try {
			String text = new String(CryptoUtil.decryptSymmetric(session.getSessionKey(), session.getSalt(), prompt)) + ", " + "Who's there?";
			byte[] answer = CryptoUtil.encryptSymmetric(session.getSessionKey(), session.getSalt(), text.getBytes());
			if ((answer == null) || (answer.length == 0)) {
				sessions.remove(sessionId);
			}
			session.setAcknowledged(true);
			return answer;
		}
		catch (Exception e) {
			sessions.remove(sessionId);
			throw e;
		}
	}
	
	public User createUser(BigInteger sessionId, String username, String password, String firstname, String middlename, String lastname, String email,  Role role) {
		if (sessionId == null) {
			return null;
		}
		Session session = sessions.get(sessionId);
		if (session == null) {
			return null;
		}	
		return  new User(username, password, firstname, middlename, lastname, email, role);
	}
}