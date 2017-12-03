package no.fasmer.bookshelf.rest;

import java.io.Serializable;

public class ValidationResult implements Serializable {
    
    private final boolean valid;
    private final boolean expired;

    public ValidationResult(boolean valid, boolean expired) {
        this.valid = valid;
        this.expired = expired;
    }
    
    public boolean isValid() {
        return valid;
    }

    public boolean isExpired() {
        return expired;
    }
    
    public boolean isNotExpired() {
        return !expired;
    }
    
}
