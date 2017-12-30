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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@Entity
@Table(name = EntityNames.BOOK)
@NamedQueries(value = {
    @NamedQuery(
            name = "findByIsbn13",
            query = "SELECT b FROM Book b WHERE b.isbn13 = :isbn13"
    ),
    @NamedQuery(
            name = "findByTitle",
            query = "SELECT b FROM Book b WHERE b.title LIKE :title"
    )
})
@Indexed
@AnalyzerDef(name = "bookanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
            @TokenFilterDef(factory = LowerCaseFilterFactory.class),
            @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                @Parameter(name = "language", value = "English")
            })
        })
public class Book implements Serializable {

    @Id
    @NotNull
    private String isbn13;

    @NotNull
    @Field
    private String isbn10;

    @NotNull
    @Field
    @Analyzer(definition = "bookanalyzer")
    private String title;

    // Book is owner of this many-to-many relationship
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author", 
            joinColumns = @JoinColumn(name = "books_isbn13", referencedColumnName = "isbn13", table = EntityNames.BOOK), 
            inverseJoinColumns = @JoinColumn(name = "authors_id", referencedColumnName = "id", table = EntityNames.AUTHOR))
    private Collection<Author> authors;
    
    // Book is owner of this many-to-many relationship
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_tag", 
            joinColumns = @JoinColumn(name = "books_isbn13", referencedColumnName = "isbn13", table = EntityNames.BOOK), 
            inverseJoinColumns = @JoinColumn(name = "tags_id", referencedColumnName = "id", table = EntityNames.TAG))
    private Collection<Tag> tags;
    
    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private Collection<Bookshelf> bookshelves;
    
    // the inverse side
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private Collection<Review> reviews;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date published;

    @NotNull
    private String publisher;

    @NotNull
    private Integer edition;

    @NotNull
    private Integer numPages;
    
    private String pictureUrl;

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

    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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
