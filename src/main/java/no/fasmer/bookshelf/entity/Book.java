package no.fasmer.bookshelf.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = EntityNames.BOOK)
@NamedQueries(value = {
    @NamedQuery(
            name = "findByIsbn13",
            query = "SELECT b FROM Book b WHERE b.isbn13 = :isbn13"
    )
})
public class Book implements Serializable {

    @Id
    //@Pattern(regexp = "^\\d{13}", message = "{invalid.isbn13}")
    @NotNull
    private String isbn13;

    //@Pattern(regexp = "^\\d{10}", message = "{invalid.isbn10}")
    @NotNull
    private String isbn10;

    @NotNull
    private String title;

    // Book is owner of this many-to-many relationship
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author", 
            joinColumns = @JoinColumn(name = "books_isbn13", referencedColumnName = "isbn13", table = EntityNames.BOOK), 
            inverseJoinColumns = @JoinColumn(name = "authors_id", referencedColumnName = "id", table = EntityNames.AUTHOR))
    private Collection<Author> authors;
    
    // Book is owner of this many-to-many relationship
    @ManyToMany
    @JoinTable(name = "book_tag", 
            joinColumns = @JoinColumn(name = "books_isbn13", referencedColumnName = "isbn13", table = EntityNames.BOOK), 
            inverseJoinColumns = @JoinColumn(name = "tags_id", referencedColumnName = "id", table = EntityNames.TAG))
    private Collection<Tag> tags;
    
    @ManyToMany(mappedBy = "books")
    private Collection<Bookshelf> bookshelves;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date published;

    @NotNull
    private String publisher;

    @NotNull
    private Integer edition;

    @NotNull
    private Integer numPages;

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<Author> authors) {
        this.authors = authors;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    public Collection<Bookshelf> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(Collection<Bookshelf> bookshelves) {
        this.bookshelves = bookshelves;
    }
    
    public String getAuthorSummary() {
        if (authors == null || authors.isEmpty()) {
            return "";
        }
        
        return authors
                .stream()
                .map(x -> x.getFirstName() + " " + x.getLastName())
                .collect(Collectors.joining());
    }

}
