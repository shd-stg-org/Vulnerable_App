package com.example.vulnerable;

import java.security.MessageDigest;
import java.util.Random;

/**
 * HIGH/MEDIUM: Cryptography and hashing vulnerabilities
 */
public class CryptographyVulnerabilities {

    /**
     * HIGH: Using weak hashing algorithm (MD5)
     * Severity: HIGH
     */
    public String hashPasswordWithMD5(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(password.getBytes());
        
        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    /**
     * MEDIUM: Using weak random number generator for security purposes
     * Severity: MEDIUM
     */
    public String generateSecurityToken() {
        Random random = new Random();  // VULNERABLE: Not cryptographically secure
        StringBuilder token = new StringBuilder();
        
        for (int i = 0; i < 32; i++) {
            token.append(random.nextInt(16));
        }
        return token.toString();
    }

    /**
     * MEDIUM: Using SHA-1 which is considered weak
     * Severity: MEDIUM
     */
    public String hashWithSHA1(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");  // Weak algorithm
        byte[] messageDigest = md.digest(data.getBytes());
        
        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
