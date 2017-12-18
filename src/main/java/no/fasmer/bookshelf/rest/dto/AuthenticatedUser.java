package no.fasmer.bookshelf.rest.dto;

import java.io.Serializable;
import java.util.Date;

public class AuthenticatedUser implements Serializable {
    
    private final String username;
    private final String hashedApiKey;
    private final Date expires;

    public AuthenticatedUser(String username, String hashedApiKey, Date expires) {
        this.username = username;
        this.hashedApiKey = hashedApiKey;
        this.expires = expires;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedApiKey() {
        return hashedApiKey;
    }

    public Date getExpires() {
        return expires;
    }
    
}
