package com.example.graalvm;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class SymmetricEncryptionPDFExample {

    public static void main(String[] args) throws Exception {
      
        SecretKey secretKey = generateKey();

       
        String inputPdfPath = "C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\Task-1\\src\\main\\resources\\Java Cryptography - Quick Guide.pdf";
        String encryptedPdfPath = "C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\Task-1\\src\\main\\resources\\encrypted.pdf";
        String decryptedPdfPath = "C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\Task-1\\src\\main\\resources\\decrypted.pdf";

        createSamplePdf(inputPdfPath);

       
        String pdfContent = readPdfContent(inputPdfPath);

        
        String encryptedContent = encrypt(pdfContent, secretKey);
        Files.write(Paths.get(encryptedPdfPath), encryptedContent.getBytes());

        
        String encryptedPdfContent = new String(Files.readAllBytes(Paths.get(encryptedPdfPath)));
        String decryptedContent = decrypt(encryptedPdfContent, secretKey);
        writePdfContent(decryptedPdfPath, decryptedContent);

        System.out.println("Encryption and decryption completed successfully.");
    }

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); 
        return keyGen.generateKey();
    }

    public static String encrypt(String data, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedData, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    public static void createSamplePdf(String filePath) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            document.save(filePath);
        }
    }

    public static String readPdfContent(String filePath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        }
    }

    public static void writePdfContent(String filePath, String content) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            document.save(filePath);
        }
    }
}
