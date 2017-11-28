package no.fasmer.bookshelf.utils;

import java.io.Serializable;

public class KeyBundle implements Serializable {
    
    private final String key;
    private final String password;
    private final String salt;

    public KeyBundle(String key, String password, String salt) {
        this.key = key;
        this.password = password;
        this.salt = salt;
    }

    public String getKey() {
        return key;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }
    
}
