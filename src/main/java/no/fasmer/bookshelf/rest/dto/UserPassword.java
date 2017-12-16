package no.fasmer.bookshelf.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class UserPassword implements Serializable {

    private String username = null;
    private String password = null;

    public UserPassword() {
    }
    
    public UserPassword(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
