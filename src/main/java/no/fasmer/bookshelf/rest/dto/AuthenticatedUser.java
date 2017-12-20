package no.fasmer.bookshelf.rest.dto;

import java.io.Serializable;
import java.util.Date;

public class AuthenticatedUser implements Serializable {
    
    private final String username;
    private final String apiKey;
    private final Date expires;

    public AuthenticatedUser(String username, String apiKey, Date expires) {
        this.username = username;
        this.apiKey = apiKey;
        this.expires = expires;
    }

    public String getUsername() {
        return username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public Date getExpires() {
        return expires;
    }
    
}
