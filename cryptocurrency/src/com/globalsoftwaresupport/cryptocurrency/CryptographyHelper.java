package com.globalsoftwaresupport.cryptocurrency;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

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

    public static byte[] sign(PrivateKey privateKey, String input) {
        Signature signature;
        byte[] output = new byte[0];

        try {
            //we use bouncy castle for ECC
            signature = Signature.getInstance("ECDSA", "BC");
            signature.initSign(privateKey);
            signature.update(input.getBytes());
            output = signature.sign();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return output;
    }

    public static KeyPair ellipticCurveCrypto() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECGenParameterSpec params = new ECGenParameterSpec("prime256v1");
            keyPairGenerator.initialize(params);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verify(PublicKey publicKey, String data, byte[] signature) {
        try {
            Signature ecdsaSignature = Signature.getInstance("ECDSA", "BC");
            ecdsaSignature.initVerify(publicKey);
            ecdsaSignature.update(data.getBytes());
            return ecdsaSignature.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
