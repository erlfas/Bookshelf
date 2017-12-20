package no.fasmer.bookshelf.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries(value = {
    @NamedQuery(
            name = "findApiKey",
            query = "SELECT a FROM ApiKey a WHERE a.apiKey = :ak"
    ),
    @NamedQuery(
            name = "findByUsernameAndApiKey",
            query = "SELECT a FROM ApiKey a WHERE a.apiKey = :ak AND a.bookshelfUser.username = :un"
    ),
    @NamedQuery(
            name = "findByUsername",
            query = "SELECT a FROM ApiKey a WHERE a.bookshelfUser.username = :un AND a.bookshelfUser.password = :pwd"
    )
})
public class ApiKey implements Serializable {
    
    @SequenceGenerator(name = "ApiKey_Gen", sequenceName = "ApiKey_Seq")
    @Id
    @GeneratedValue(generator = "ApiKey_Gen")
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "bookshelfUser_id")
    private BookshelfUser bookshelfUser;
    
    @NotNull
    @Column(unique = true)
    private String apiKey;
    
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date expires;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookshelfUser getBookshelfUser() {
        return bookshelfUser;
    }

    public void setBookshelfUser(BookshelfUser bookshelfUser) {
        this.bookshelfUser = bookshelfUser;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }
    
}
