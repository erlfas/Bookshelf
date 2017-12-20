package no.fasmer.bookshelf.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"bookshelfUser_id", "title"}))
@NamedQueries(value = {
    @NamedQuery(
            name = "getBookshelf",
            query = "SELECT b FROM Bookshelf b WHERE b.bookshelfUser.id = :userId AND b.title = :title"
    ),
    @NamedQuery(
            name = "getBookshelfByUsernameAndTitle",
            query = "SELECT b FROM Bookshelf b WHERE b.bookshelfUser.username = :u AND b.title = :t"
    )
})
public class Bookshelf implements Serializable {
    
    @SequenceGenerator(name = "Bookshelf_Gen", sequenceName = "Bookshelf_Seq")
    @Id
    @GeneratedValue(generator = "Bookshelf_Gen")
    private Long id;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "bookshelfUser_id")
    private BookshelfUser bookshelfUser;
    
    @NotNull
    private String title;
    
    @OneToMany
    private List<Book> books;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Book> getBooks() {
        if (books == null) {
            books = new ArrayList<>();
        }
        
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
}
