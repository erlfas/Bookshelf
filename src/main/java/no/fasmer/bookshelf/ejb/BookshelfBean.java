package no.fasmer.bookshelf.ejb;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.dao.BookDao;
import no.fasmer.bookshelf.dao.BookshelfDao;
import no.fasmer.bookshelf.dao.BookshelfUserDao;
import no.fasmer.bookshelf.entity.Book;
import no.fasmer.bookshelf.entity.Bookshelf;
import no.fasmer.bookshelf.entity.BookshelfUser;
import no.fasmer.bookshelf.rest.dto.BookshelfResponse;
import no.fasmer.bookshelf.rest.enums.RestStatus;
import org.apache.commons.lang3.StringUtils;

@Stateless
public class BookshelfBean {
    
    @Inject
    private BookDao bookDao;
    
    @Inject
    private BookshelfDao bookshelfDao;
    
    @Inject
    private BookshelfUserDao bookshelfUserDao;
    
    @Inject
    private Logger logger;
    
    public List<Bookshelf> getAllBookshelves(String username) {
        return bookshelfDao.getAllBookshelvesByUsername(username);
    }
    
    public BookshelfResponse addBookToBookshelf(String title, String username, Book book) {
        if (StringUtils.isBlank(title)) {
            return new BookshelfResponse(RestStatus.INVALID_INPUT, null);
        }
        
        if (StringUtils.isBlank(username)) {
            return new BookshelfResponse(RestStatus.INVALID_INPUT, null);
        }
        
        if (book == null) {
            return new BookshelfResponse(RestStatus.INVALID_INPUT, null);
        }
        
        final Bookshelf bookshelf = bookshelfDao.getBookshelf(username, title);
        
        if (bookshelf == null) {
            return new BookshelfResponse(RestStatus.NOT_FOUND, null);
        }
        
        bookshelf.getBooks().add(book);
        
        bookshelfDao.persist(bookshelf);
        
        return new BookshelfResponse(RestStatus.CREATED, bookshelf);
    }
    
    public BookshelfResponse addBookToBookshelf(String title, String username, String isbn13) {
        if (StringUtils.isBlank(title)) {
            return new BookshelfResponse(RestStatus.INVALID_INPUT, null);
        }
        
        if (StringUtils.isBlank(username)) {
            return new BookshelfResponse(RestStatus.INVALID_INPUT, null);
        }
        
        if (StringUtils.isBlank(isbn13)) {
            return new BookshelfResponse(RestStatus.INVALID_INPUT, null);
        }
        
        final Bookshelf bookshelf = bookshelfDao.getBookshelf(username, title);
        
        if (bookshelf == null) {
            return new BookshelfResponse(RestStatus.NOT_FOUND, null);
        }
        
        final Book book = bookDao.findByIsbn13(isbn13);
        
        if (book == null) {
            return new BookshelfResponse(RestStatus.NOT_FOUND, null);
        }
        
        bookshelf.getBooks().add(book);
        
        bookshelfDao.persist(bookshelf);
        
        return new BookshelfResponse(RestStatus.CREATED, bookshelf);
    }
    
    public Bookshelf getBookshelfById(Long bookshelfId) {
        if (bookshelfId == null) {
            return null;
        }
        
        return bookshelfDao.find(bookshelfId);
    }
    
    public Bookshelf getBookshelf(String username, String title) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        
        if (StringUtils.isBlank(title)) {
            return null;
        }
        
        return bookshelfDao.getBookshelf(username, title);
    }
    
    public BookshelfResponse addBookshelf(String username, String title) {
        if (username == null) {
            return new BookshelfResponse(RestStatus.INVALID_INPUT, null);
        }
        
        if (StringUtils.isBlank(title)) {
            return new BookshelfResponse(RestStatus.INVALID_INPUT, null);
        }
        
        final BookshelfUser bookshelfUser = bookshelfUserDao.findByUsername(username);
        
        if (bookshelfUser == null) {
            return new BookshelfResponse(RestStatus.NOT_FOUND, null);
        }
        
        final boolean userHasBookshelf = bookshelfDao.userHasBookshelf(username, title);
        
        if (userHasBookshelf) {
            return new BookshelfResponse(RestStatus.CONFLICT, null);
        }
        
        final Bookshelf bookshelf = new Bookshelf();
        bookshelf.setTitle(title);
        bookshelf.setBookshelfUser(bookshelfUser);
        bookshelfDao.persist(bookshelf);
        
        return new BookshelfResponse(RestStatus.CREATED, bookshelf);
    }
    
}
