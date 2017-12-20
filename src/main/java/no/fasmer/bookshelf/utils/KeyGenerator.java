package no.fasmer.bookshelf.utils;

import java.util.UUID;

public class KeyGenerator {
    
    public static KeyBundle getBundle(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key must not be null");
        }
        
        if (key.length() != 32) {
            throw new IllegalArgumentException("Length of key must be 32.");
        }
        
        return new KeyBundle(key);
    }
    
    public static KeyBundle generate() {
        final UUID uuid = UUID.randomUUID();
        final String key = uuid.toString().replace("-", "");
        return new KeyBundle(key);
    }
    
}
