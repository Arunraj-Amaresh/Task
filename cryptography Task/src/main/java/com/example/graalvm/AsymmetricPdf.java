package com.example.graalvm;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

public class AsymmetricPdf {
    
    public static void main(String[] args) throws Exception {
       
        Security.addProvider(new BouncyCastleProvider());

        
        KeyPair keyPair = generateKeyPair();

       
        String inputPdfPath = "C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\Task-1\\src\\main\\resources\\Java Cryptography - Quick Guide.pdf";
        String encryptedPdfPath = "C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\Task-1\\src\\main\\resources\\encrypted.pdf";
        String decryptedPdfPath = "C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\Task-1\\src\\main\\resources\\decrypted.pdf";

      
        createSamplePdf(inputPdfPath);

      
        byte[] pdfBytes = Files.readAllBytes(Paths.get(inputPdfPath));

     
        byte[] encryptedPdfBytes = encrypt(pdfBytes, keyPair.getPublic());
        Files.write(Paths.get(encryptedPdfPath), encryptedPdfBytes);

       
        byte[] decryptedPdfBytes = decrypt(encryptedPdfBytes, keyPair.getPrivate());
        Files.write(Paths.get(decryptedPdfPath), decryptedPdfBytes);

        System.out.println("Encryption and decryption completed successfully.");
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    public static byte[] encrypt(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    public static void createSamplePdf(String filePath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            document.save(filePath);
        }
    }
}
