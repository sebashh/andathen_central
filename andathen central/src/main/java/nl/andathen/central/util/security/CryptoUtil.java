package nl.andathen.central.util.security;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class CryptoUtil {
	public static final String SYMMETRIC_ALGORITHM = "AES";
	public static final int ITERATIONS = 10000;		
	public static final BigInteger PUBLIC_EXPONENT = new BigInteger( "65537", 16);			// Needs to be a prime, higher is stronger but slower. 65537 and 10001 seem to work fine.
	public static final int KEYSIZE = 2048; 												// Specs define only 1024, 2048 and 3072 are allowed. Larger blocks are more secure, but slow
	public static final int CERTAINTY = 11;													// Can go up to 80 or more, but the gain is little
	public static final String SYMMETRIC_KEY_ALGORITHM = "PBEWithSHA1And256BitAES-CBC-BC";
	public static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
	public static final String SECURITY_PROVIDER = "BC";
	
	public static byte[] decryptAsymmetric(byte[] data, AsymmetricKeyParameter privateKey) throws InvalidCipherTextException {
	    AsymmetricBlockCipher engine = new RSAEngine();
	    engine.init(false, privateKey);
	    return engine.processBlock(data, 0, data.length);
	}
	
	public static String decryptAsymmetric(String data, AsymmetricKeyParameter privateKey) throws InvalidCipherTextException {
		return new String(decryptAsymmetric(data.getBytes(), privateKey));
	}

	public static SecretKey decryptSessionKey(byte[] encrypted, AsymmetricKeyParameter privateKey) throws InvalidCipherTextException {
	    AsymmetricBlockCipher engine = new RSAEngine();
	    engine.init(false, privateKey);
	    byte[] hexEncodedCipher = engine.processBlock(encrypted, 0, encrypted.length);
	    return new SecretKeySpec(hexEncodedCipher, SYMMETRIC_KEY_ALGORITHM);
	}

	public static byte[] decryptSymmetric(SecretKeySpec secretKey, final byte[] salt, final byte[] cypherText) throws Exception {
		initBouncyCastle();
		PBEParameterSpec cipherSpec = new PBEParameterSpec(salt, ITERATIONS);
	    Cipher cipher = Cipher.getInstance(SYMMETRIC_KEY_ALGORITHM, SECURITY_PROVIDER);
	    cipher.init(Cipher.DECRYPT_MODE, secretKey, cipherSpec);
	    byte[] decryptedBytes = cipher.doFinal(cypherText);
	    return decryptedBytes;
	}

	public static byte[] encryptAsymmetric(byte[] data, AsymmetricKeyParameter publicKey) throws Exception {
	    RSAEngine engine = new RSAEngine();
	    engine.init(true, publicKey);
	    return engine.processBlock(data, 0, data.length);
	}
	
	public static String encryptAsymmetric(String data, AsymmetricKeyParameter publicKey) throws Exception {
		return new String(encryptAsymmetric(data.getBytes(), publicKey));
	}

	public static byte[] encryptSymmetric(SecretKeySpec secretKey, final byte[] salt, final byte[] plainText) throws Exception {
		initBouncyCastle();
		PBEParameterSpec cipherSpec = new PBEParameterSpec(salt, ITERATIONS);
	    Cipher cipher = Cipher.getInstance(SYMMETRIC_KEY_ALGORITHM, SECURITY_PROVIDER);
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey, cipherSpec);
	    byte[] encryptedBytes = cipher.doFinal(plainText);
	    return encryptedBytes;
	}
	
	public static void initBouncyCastle( ) throws NoSuchAlgorithmException, NoSuchProviderException {
		Security.setProperty("crypto.policy", "unlimited");
		Security.addProvider(new BouncyCastleProvider());
		CryptoServicesRegistrar.setSecureRandom(SecureRandom.getInstanceStrong());
	}
	
	
    public static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
