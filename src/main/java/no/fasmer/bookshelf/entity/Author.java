package no.fasmer.bookshelf.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = EntityNames.AUTHOR)
@NamedQueries(value = {
    @NamedQuery(
            name = "findByName",
            query = "SELECT a FROM Author a WHERE a.firstName = :fn AND a.lastName = :ln"
    )
})
@Indexed
public class Author implements Serializable {

    @SequenceGenerator(name = "Author_Gen", sequenceName = "Author_Seq")
    @Id
    @GeneratedValue(generator = "Author_Gen")
    private Long id;
    
    @NotNull
    @Field
    private String firstName;
    
    @NotNull
    @Field
    private String lastName;
    
    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
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
