package com.company;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class CryptographyHelper {
    public static String hash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder builder = new StringBuilder();
            for (byte b : hash) {
                String hexadecimal = Integer.toHexString(0xff & b);
                if (hexadecimal.length() == 1) builder.append('0');
                builder.append(hexadecimal);
            }
            return builder.toString();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
