package com.example.graalvm;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.util.Base64;

public class KeyPairExample {
    public static void main(String[] args) throws Exception {
        
    	
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); 
        KeyPair keyPair = keyGen.generateKeyPair();
        
        
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        
        
        String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        
       
        System.out.println("Public Key (Base64): " + publicKeyBase64);
        System.out.println("Private Key (Base64): " + privateKeyBase64);
    }
}
