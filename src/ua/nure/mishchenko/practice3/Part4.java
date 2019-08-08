package ua.nure.mishchenko.practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class Part4 {

    public static String hash(String input, String algorithm)
            throws NoSuchAlgorithmException {
            MessageDigest md =
                    MessageDigest.getInstance(algorithm);
            md.reset();
            md.update(input.getBytes());
            byte[] hash = md.digest();
            StringBuilder hexString = new StringBuilder();
            StringBuilder hex = new StringBuilder();
            for (byte var : hash) {
                hex.append(Integer.toHexString(0xFF & var)
                        .toUpperCase(Locale.ENGLISH));
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
                hex.setLength(0);
            }
            return hexString.toString();
    }

    public static void main(String[] args){

        try {
            System.out.println(hash("asdf", "MD5"));
            System.out.println(hash("asdf", "SHA-256"));
            System.out.println(hash("asdf", "SHA-512"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
