package no.fasmer.bookshelf.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Tag implements Serializable {
    
    @SequenceGenerator(name = "Tag_Gen", sequenceName = "Tag_Seq")
    @Id
    @GeneratedValue(generator = "Tag_Gen")
    private Long id;
    
    private String name;
    
    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Collection<Book> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }
    
}
