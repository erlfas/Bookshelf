package no.fasmer.bookshelf.utils;

import java.util.UUID;

public class KeyGenerator {
    
    public static KeyBundle extract(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        
        if (key.length() != 32) {
            throw new IllegalArgumentException("Length of key must be 32.");
        }
        
        return new KeyBundle(key, key.substring(0, 10), key.substring(10));
    }
    
    public static KeyBundle generate() {
        final UUID uuid = UUID.randomUUID();
        final String key = uuid.toString().replace("-", "");
        final String password = key.substring(0, 10); // 10
        final String salt = key.substring(10, key.length()); // 22
        return new KeyBundle(key, password, salt);
    }
    
}
