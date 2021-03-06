package no.fasmer.bookshelf.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = EntityNames.BOOKSHELFUSER)
@NamedQueries(value = {
    @NamedQuery(
            name = "findBookshelfUser",
            query = "SELECT b FROM BookshelfUser b WHERE b.username = :un AND b.password = :pwd"
    ),
    @NamedQuery(
            name = "findBookshelfUserByUsername",
            query = "SELECT b FROM BookshelfUser b WHERE b.username = :un"
    )
})
public class BookshelfUser implements Serializable {
    
    @SequenceGenerator(name = "User_Gen", sequenceName = "User_Seq")
    @Id
    @GeneratedValue(generator = "User_Gen")
    private Long id;
    
    @NotNull
    @Column(unique = true)
    private String username;
    
    @NotNull
    private String password;
    
    @NotNull
    private String firstName;
    
    @NotNull
    private String lastName;
    
    @NotNull
    private String phone;
    
    @Enumerated(EnumType.STRING)
    private SecurityLevel securityLevel;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookshelfUser")
    private Collection<Review> reviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }
    
}
