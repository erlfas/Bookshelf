package no.fasmer.bookshelf.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "review")
public class Review implements Serializable {

    @SequenceGenerator(name = "Review_Gen", sequenceName = "Review_Seq")
    @Id
    @GeneratedValue(generator = "Review_Gen")
    private Long id;
    
    @Lob
    @NotNull
    private String text;
    
    @ManyToOne
    @JoinColumn(name = "BOOK_ISBN13")
    private Book book;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private BookshelfUser bookshelfUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookshelfUser getBookshelfUser() {
        return bookshelfUser;
    }

    public void setBookshelfUser(BookshelfUser bookshelfUser) {
        this.bookshelfUser = bookshelfUser;
    }
    
}
