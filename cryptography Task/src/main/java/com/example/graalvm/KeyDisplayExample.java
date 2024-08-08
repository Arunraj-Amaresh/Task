package com.example.graalvm;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class KeyDisplayExample {
    public static void main(String[] args) throws Exception {
        
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); 
        SecretKey secretKey = keyGen.generateKey();
        
        
        String keyBase64 = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        
       
        System.out.println("Generated Key (Base64): " + keyBase64);
    }
}