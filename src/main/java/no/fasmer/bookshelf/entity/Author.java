package no.fasmer.bookshelf.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Author implements Serializable {

    @SequenceGenerator(name = "Author_Gen", sequenceName = "Author_Seq")
    @Id
    @GeneratedValue(generator = "Author_Gen")
    private Long id;
    
    @NotNull
    private String firstName;
    
    @NotNull
    private String lastName;
    
    // Book is owner of this many-to-many relationship
    // Author is the inverse side
    @ManyToMany(mappedBy = "authors")
    private Collection<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

}
