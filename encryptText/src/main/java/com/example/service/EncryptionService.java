package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EncryptionService {
	private static final int BASE_SHIFT = 3;

	public String encrypt(String text, String salt) {
		if (salt == null || salt.isEmpty()) {
			salt = "Solve";
		}

		StringBuilder encrypted = new StringBuilder();
		int saltIndex = 0;

		for (char character : text.toCharArray()) {
			if (Character.isLetter(character)) {
				char base = Character.isUpperCase(character) ? 'A' : 'a';
				int saltShift = BASE_SHIFT + (saltIndex < salt.length() ? salt.charAt(saltIndex) : 0);
				char encryptedChar = (char) ((character - base + saltShift) % 26 + base);
				encrypted.append(encryptedChar);
				saltIndex = (saltIndex + 1) % salt.length();
			} else {
				encrypted.append(character);
			}
		}
		return encrypted.toString();
	}

	public String encrypt2(String text, String salt) {
		if (salt == null || salt.isEmpty()) {
			salt = "Solve"; // Default salt is your name
		}
		
		try {
			// Get SHA-256 instance
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// Combine text and salt
			String saltedText = text + salt;

			// Generate hash
			byte[] hashBytes = digest.digest(saltedText.getBytes(StandardCharsets.UTF_8));
			
			// Convert bytes to hexadecimal string
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 algorithm not found", e);
		}
	}
}
