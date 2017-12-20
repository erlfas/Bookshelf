package no.fasmer.bookshelf.utils;

import java.io.Serializable;

public class KeyBundle implements Serializable {
    
    private final String key;

    public KeyBundle(String key) {
        if (key == null || key.length() != 32) {
            throw new IllegalArgumentException("Must be string of length 32");
        }
        this.key = key;
    }

    public String getKey() {
        return key;
    }
    
}
