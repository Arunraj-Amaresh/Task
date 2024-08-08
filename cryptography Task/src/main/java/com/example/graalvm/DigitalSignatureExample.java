package com.example.graalvm;

import java.security.*;
import java.util.Base64;

public class DigitalSignatureExample {
	public static void main(String[] args) throws Exception {
		String message = "C:\\Users\\BSIT-021\\Documents\\workspace-spring-tool-suite-4-4.23.1.RELEASE\\Task-1\\src\\main\\resources\\Java Cryptography - Quick Guide.pdf";

		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

		keyGen.initialize(2048);

		KeyPair keyPair = keyGen.generateKeyPair();

		PrivateKey privateKey = keyPair.getPrivate();

		PublicKey publicKey = keyPair.getPublic();

		Signature signature = Signature.getInstance("SHA256withRSA");

		signature.initSign(privateKey);

		signature.update(message.getBytes());

		byte[] digitalSignature = signature.sign();

		String signatureStr = Base64.getEncoder().encodeToString(digitalSignature);

		System.out.println("Digital Signature: " + signatureStr);

		signature.initVerify(publicKey);

		signature.update(message.getBytes());

		boolean isVerified = signature.verify(Base64.getDecoder().decode(signatureStr));

		System.out.println("Signature Verified: " + isVerified);
	}
}