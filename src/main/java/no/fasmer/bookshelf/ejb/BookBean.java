package no.fasmer.bookshelf.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.dao.AuthorDao;
import no.fasmer.bookshelf.dao.BookDao;
import no.fasmer.bookshelf.entity.Author;
import no.fasmer.bookshelf.entity.Book;
import no.fasmer.bookshelf.mapper.Mapper;
import no.fasmer.bookshelf.rest.enums.RestStatus;
import org.apache.commons.lang3.StringUtils;

@Stateless
public class BookBean {

    @Inject
    private BookDao bookDao;
    
    @Inject
    private AuthorDao authorDao;

    @Inject
    private Logger logger;
    
    public List<no.fasmer.bookshelf.model.Book> findBooksByTitle(String title) {
        final List<no.fasmer.bookshelf.entity.Book> results = bookDao.findByTitle(title);
        
        if (results == null || results.isEmpty()) {
            return null;
        }
        
        final List<no.fasmer.bookshelf.model.Book> books = new ArrayList<>();
        for (no.fasmer.bookshelf.entity.Book book : results) {
            books.add(Mapper.map(book));
        }
        
        return books;
    }
    
    public Book getBook(String isbn13) {
        if (StringUtils.isBlank(isbn13)) {
            return null;
        }
        
        return bookDao.findByIsbn13(isbn13);
    }

    public RestStatus addBook(no.fasmer.bookshelf.entity.Book book) {
        try {
            final List<Author> authors = new ArrayList<>();
            for (Author author : book.getAuthors()) {
                final Author existing = authorDao.findByName(author.getFirstName(), author.getLastName());
                if (existing == null) {
                    authorDao.persist(author);
                    authors.add(author);
                } else {
                    authors.add(existing);
                }
            }
            
            book.setAuthors(authors);
            
            bookDao.persist(book);

            return RestStatus.CREATED;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Book not persisted.", e);
            return RestStatus.INTERNAL_ERROR;
        }
    }

    public RestStatus deleteBook(final String isbn13) {
        if (StringUtils.isBlank(isbn13)) {
            return RestStatus.INVALID_INPUT;
        }

        final no.fasmer.bookshelf.entity.Book book = bookDao.findByIsbn13(isbn13);

        if (book == null) {
            return RestStatus.NOT_FOUND;
        }

        try {
            bookDao.remove(book);
            return RestStatus.OK;
        } catch (Exception e) {
            return RestStatus.INTERNAL_ERROR;
        }
    }
    
    public RestStatus updateBook(no.fasmer.bookshelf.entity.Book updatedBook) {
        if (updatedBook == null) {
            return RestStatus.INVALID_INPUT;
        }
        
        final no.fasmer.bookshelf.entity.Book existing = bookDao.findByIsbn13(updatedBook.getIsbn13());
        
        if (existing == null) {
            return RestStatus.NOT_FOUND;
        }
        
        existing.setEdition(updatedBook.getEdition());
        existing.setNumPages(updatedBook.getNumPages());
        existing.setPublished(updatedBook.getPublished());
        existing.setPublisher(updatedBook.getPublisher());
        existing.setTags(updatedBook.getTags());
        existing.setTitle(updatedBook.getTitle());
        
        return RestStatus.OK;
    }

}
