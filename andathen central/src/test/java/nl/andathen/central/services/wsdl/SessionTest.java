package nl.andathen.central.services.wsdl;

import static nl.andathen.central.domain.person.User.Role.USER;

import org.apache.commons.lang3.SerializationUtils;

import nl.andathen.central.domain.person.User;
import nl.andathen.central.domain.person.User.Role;
import nl.andathen.central.services.wsdl.session.SessionService;
import nl.andathen.central.util.security.CryptoUtil;
import nl.andathen.central.util.session.Session;
import nl.andathen.central.util.session.SessionClient;

public class SessionTest {
	public static void main(String[] args) throws Exception {
		new SessionTest();
	}
	
	private SessionTest() throws Exception  {
		SessionClient sc = new SessionClient();
		Session session = sc.getSession();
		SessionService ss = sc.getSessionService();
		createTestUser (ss, session, "hdrillen", "OokWelkom", "Harald", null, "Drillenburg", "harald.drillenburg@inholland.nl", USER);
	}
	
	public void createTestUser(SessionService ss, Session session, String username, String password, String firstname, String middlename, String lastname, String email,  Role role) throws Exception  {
		// Encrypt data
		byte[] encryptedUsername = CryptoUtil.encryptSymmetric(session.getSessionKey(), session.getSalt(), SerializationUtils.serialize(username));
		byte[] encryptedPassword = CryptoUtil.encryptSymmetric(session.getSessionKey(), session.getSalt(), SerializationUtils.serialize(password));
		byte[] encryptedFirstname = CryptoUtil.encryptSymmetric(session.getSessionKey(), session.getSalt(), SerializationUtils.serialize(firstname));
		byte[] encryptedMiddlename = CryptoUtil.encryptSymmetric(session.getSessionKey(), session.getSalt(), SerializationUtils.serialize(middlename));
		byte[] encryptedLastname = CryptoUtil.encryptSymmetric(session.getSessionKey(), session.getSalt(), SerializationUtils.serialize(lastname));
		byte[] encryptedEmail = CryptoUtil.encryptSymmetric(session.getSessionKey(), session.getSalt(), SerializationUtils.serialize(email));
		byte[] encryptedRole = CryptoUtil.encryptSymmetric(session.getSessionKey(), session.getSalt(), SerializationUtils.serialize(role));
		
		// Send request over WSDL
		byte[] answer = ss.createUser(session.getSessionId(), encryptedUsername, encryptedPassword, encryptedFirstname, encryptedMiddlename, encryptedLastname, encryptedEmail,  encryptedRole);
		User user = SerializationUtils.deserialize(CryptoUtil.decryptSymmetric(session.getSessionKey(), session.getSalt(), answer));
		System.out.println(user);
	}
}