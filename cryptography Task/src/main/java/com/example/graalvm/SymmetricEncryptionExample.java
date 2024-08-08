package com.example.graalvm;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@SuppressWarnings("unused")
public class SymmetricEncryptionExample {
	public static void main(String[] args) throws Exception {
		
		String plaintext = "ARUN RAJ";

		KeyGenerator keyGen = KeyGenerator.getInstance("AES");

		keyGen.init(128);

		SecretKey secretKey = keyGen.generateKey();

		Cipher cipher = Cipher.getInstance("AES");

		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());

		String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

		System.out.println("Encrypted Text: " + encryptedText);

		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

		String decryptedText = new String(decryptedBytes);

		System.out.println("Decrypted Text: " + decryptedText);

		String keyHex = bytesToHex(secretKey.getEncoded());

		System.out.println("Generated Key (Hex): " + keyHex);

	}

	public static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

}