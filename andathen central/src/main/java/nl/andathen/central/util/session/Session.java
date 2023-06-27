package nl.andathen.central.util.session;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.Security;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import jakarta.enterprise.context.SessionScoped;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import nl.andathen.central.util.security.CryptoUtil;

@SessionScoped
public class Session implements Comparable<Session>, Serializable, Cloneable {
	public static final String SYMMETRIC_KEY_ALGORITHM = "PBEWithSHA1And256BitAES-CBC-BC";
	private static final long serialVersionUID = 1L;
	private byte[] sessionId;
	private transient boolean valid = false;
	private transient boolean acknowledged = false;
	private SecretKeySpec sessionKey;
	private byte[] encryptedSessionKey;
	private LocalDateTime sessionStart;
	private LocalDateTime lastAction;
	private LocalDateTime sessionEnd;
	private byte[] salt;
	private Map<LocalDateTime, String> actions;
	private SecureRandom random = null;
	
	private Session(boolean dummy) {
		try {
			this.random = SecureRandom.getInstanceStrong();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public Session() throws Exception {
		this.random = SecureRandom.getInstanceStrong();
		this.sessionId = generateId().toByteArray();
		this.sessionStart = LocalDateTime.now();
		this.sessionEnd = null;
		this.lastAction = null;
		this.actions = new TreeMap<>();
		this.sessionKey = generateSessionKey();
		this.acknowledged = false;
		this.valid = true;
		this.salt = getNextSalt();
		this.addAction(true);
	}
	
	@Override
	protected Session clone() {
		Session result = new Session(false);
		result.sessionId = this.sessionId;
		result.valid = this.valid;
		result.sessionKey = new SecretKeySpec(sessionKey.getEncoded(), SYMMETRIC_KEY_ALGORITHM);
		result.sessionStart = this.sessionStart;
		result.lastAction = this.lastAction;
		result.sessionEnd = this.sessionEnd;
		result.salt = this.salt;
		result.acknowledged = this.acknowledged;
		result.actions = new TreeMap<>();
		return result;
	}
	
	public void encryptKeys(AsymmetricKeyParameter userPublicKey) throws Exception {
		this.encryptedSessionKey = CryptoUtil.encryptAsymmetric(sessionKey.getEncoded(), userPublicKey);
		this.sessionKey = null;
		this.sessionId = CryptoUtil.encryptAsymmetric(sessionId, userPublicKey);
	}
	
	public void decryptKeys(AsymmetricKeyParameter userPrivateKey) throws Exception {
		byte[] decryptedSessionKey = CryptoUtil.decryptAsymmetric(encryptedSessionKey, userPrivateKey);
		this.sessionKey = new SecretKeySpec(decryptedSessionKey, SYMMETRIC_KEY_ALGORITHM);
		this.encryptedSessionKey = null;
		this.sessionId = CryptoUtil.decryptAsymmetric(sessionId, userPrivateKey);
	}

	public SecretKeySpec getSessionKey() {
		return sessionKey;
	}

	public boolean addAction(boolean succes) {
		if (valid) {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			String action = stackTraceElements[2].getMethodName();
			if (succes) {
				action = "S003 - " + action;
			}
			else {
				action = "E004 - " + action;
			}
			actions.put(LocalDateTime.now(), action);
			return true;
		}
		return false;
	}

	public boolean isValid() {
		return valid;
	}

	public void setInvalid() {
		this.valid = false;
	}

	public BigInteger getSessionId() {
		return new BigInteger(sessionId);
	}

	public void setSessionId(BigInteger sessionId) {
		this.sessionId = sessionId.toByteArray();
	}

	public LocalDateTime getSessionStart() {
		return sessionStart;
	}

	public void setSessionStart(LocalDateTime sessionStart) {
		this.sessionStart = sessionStart;
	}

	public LocalDateTime getLastAction() {
		return lastAction;
	}

	public void setLastAction(LocalDateTime lastAction) {
		this.lastAction = lastAction;
	}

	public LocalDateTime getSessionEnd() {
		return sessionEnd;
	}

	public void setSessionEnd(LocalDateTime sessionEnd) {
		this.sessionEnd = sessionEnd;
	}

	public Map<LocalDateTime, String> getActions() {
		return actions;
	}

	public void setActions(Map<LocalDateTime, String> actions) {
		this.actions = actions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sessionId);
	}

	public byte[] getSalt() {
		return salt;
	}

	public boolean isAcknowledged() {
		return acknowledged;
	}

	public void setAcknowledged(boolean acknowledged) {
		this.acknowledged = acknowledged;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		return Objects.equals(sessionId, other.sessionId);
	}

	@Override
	public String toString() {
		return "Session [sessionId=" + Hex.encodeHexString(sessionId) + " sessionStart=" + sessionStart;
	}

	@Override
	public int compareTo(Session other) {
		return Arrays.compare(this.sessionId, other.sessionId);
	}
	
	private byte[] getNextSalt() {
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}
	
	private BigInteger generateId() {
		BigInteger minLimit = BigInteger.probablePrime(256, random);
		BigInteger maxLimit = BigInteger.probablePrime(512, random);
		BigInteger big = maxLimit.subtract(minLimit);
		int len = maxLimit.bitLength();
		BigInteger result = new BigInteger(len, random);
		if (result.compareTo(minLimit) < 0) {
			result = result.add(minLimit);
		}
		if (result.compareTo(big) >= 0) {
			result = result.mod(big).add(minLimit);
		}
        return result.nextProbablePrime();
	}
	
	private SecretKeySpec generateSessionKey() throws Exception {
		Security.setProperty("crypto.policy", "unlimited");
		Security.addProvider(new BouncyCastleProvider());
		CryptoServicesRegistrar.setSecureRandom(SecureRandom.getInstanceStrong());
		
		KeyGenerator keyGenerator = KeyGenerator.getInstance(CryptoUtil.SYMMETRIC_ALGORITHM, CryptoUtil.SECURITY_PROVIDER);
		keyGenerator.init(CryptoUtil.KEYSIZE / 2, random);
		return (SecretKeySpec) keyGenerator.generateKey();
	}
}