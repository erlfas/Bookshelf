package no.fasmer.bookshelf.utils;

// https://stackoverflow.com/questions/33085493/hash-a-password-with-sha-512-in-java

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashGenerator {
    
    private final static String ENCODING = "UTF-8";
    private final static String ALGORITHM = "SHA-512";
    public final static String DEFAULT_SALT = "qxzuirbvmnty!#&/hapo";
    
    public static String generate(String passwordToHash) {
        try {
            
            final MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(DEFAULT_SALT.getBytes(ENCODING));
            final byte[] bytes = messageDigest.digest(passwordToHash.getBytes(ENCODING));
            final StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            
        }
        
        return null;
    }

    public static String generate(String passwordToHash, String salt) {
        try {
            
            final MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(salt.getBytes(ENCODING));
            final byte[] bytes = messageDigest.digest(passwordToHash.getBytes(ENCODING));
            final StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            
        }
        
        return null;
    }
    
}
