package com.example.graalvm;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.util.Base64;

public class AsymmetricEncryptionExample {
	
    public static void main(String[] args) throws Exception {
    	
        String plaintext = "Arun raj";
        
        
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        
        keyGen.initialize(2048);
        
        KeyPair keyPair = keyGen.generateKeyPair();
        
        PublicKey publicKey = keyPair.getPublic();
        
        PrivateKey privateKey = keyPair.getPrivate();
        
        
        Cipher cipher = Cipher.getInstance("RSA");
        
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        
        System.out.println("Encrypted Text: " + encryptedText);
        
        
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        
        String decryptedText = new String(decryptedBytes);
        
        System.out.println("Decrypted Text: " + decryptedText);
    }
}