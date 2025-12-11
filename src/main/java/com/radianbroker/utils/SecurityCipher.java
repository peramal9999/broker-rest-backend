package com.radianbroker.utils;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class SecurityCipher {

	private static final String SECRET_KEY = "Radian@2022";
	
	// 8-byte Salt
	private static byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3,
			(byte) 0x03 };
	// Iteration count
	private static int iterationCount = 19;

	public SecurityCipher() {

	}

	public static String encrypt(String plainText) {
		return encrypt(plainText, SECRET_KEY);
	}

	public static String decrypt(String encryptedText) {
		return decrypt(encryptedText, SECRET_KEY);
	}
	
	public static String encrypt(String plainText, String secret) {
		if (plainText == null) return null;
		
		// Key generation for enc and desc
		KeySpec keySpec = new PBEKeySpec(secret.toCharArray(), salt, iterationCount);
		SecretKey key;
		try {
			key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
			// Prepare the parameter to the ciphers
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

			// Enc process
			Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			String charSet = "UTF-8";
			byte[] in = plainText.getBytes(charSet);
			byte[] out = ecipher.doFinal(in);
			String encStr = new String(Base64.getEncoder().encode(out));
			return encStr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decrypt(String encryptedText, String secret) {
		
		if (encryptedText == null) return null;
		
		// Key generation for enc and desc
		KeySpec keySpec = new PBEKeySpec(secret.toCharArray(), salt, iterationCount);
		SecretKey key;
		try {
			key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
			// Prepare the parameter to the ciphers
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
			
			// Decryption process; same key will be used for decr
			Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			byte[] enc = Base64.getDecoder().decode(encryptedText);
			byte[] utf8 = dcipher.doFinal(enc);
			String charSet = "UTF-8";
			String plainStr = new String(utf8, charSet);
			return plainStr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}