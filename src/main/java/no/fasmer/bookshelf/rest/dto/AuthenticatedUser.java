package no.fasmer.bookshelf.rest.dto;

import java.io.Serializable;
import java.util.Date;

public class AuthenticatedUser implements Serializable {
    
    private final String username;
    private final String hashedApiKey;
    private final Date expiresDate;

    public AuthenticatedUser(String apiKey, String hashedPassword, Date expiresDate) {
        this.username = apiKey;
        this.hashedApiKey = hashedPassword;
        this.expiresDate = expiresDate;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedApiKey() {
        return hashedApiKey;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }
    
}
