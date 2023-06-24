package nl.andathen.central.services.wsdl.session;

import java.math.BigInteger;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface SessionService {
	
	@WebMethod public byte[] startSession(byte[] serializedUserPublicKey);
	@WebMethod public byte[] testSession(BigInteger sessionId, byte[] prompt); 
	
	@WebMethod public byte[] createUser(BigInteger sessionId, byte[] encryptedUsername, byte[] encryptedPassword, byte[] encryptedFirstname, byte[] encryptedMiddlename, byte[] encryptedLastname, byte[] encryptedEmail,  byte[] encryptedRole);
	
	@WebMethod public boolean logon(BigInteger sessionId, byte[] username, byte[] password); // Both encrypted and serialized
	@WebMethod public void logout(BigInteger sessionId, byte[] username, byte[] password);
}
