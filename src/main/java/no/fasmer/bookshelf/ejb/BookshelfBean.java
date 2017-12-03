package no.fasmer.bookshelf.ejb;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.dao.BookDao;
import no.fasmer.bookshelf.dao.BookshelfDao;
import no.fasmer.bookshelf.dao.BookshelfUserDao;
import no.fasmer.bookshelf.entity.Book;
import no.fasmer.bookshelf.entity.Bookshelf;
import no.fasmer.bookshelf.entity.BookshelfUser;
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
    
    public RestStatus addBookToBookshelf(Long bookshelfId, String isbn13) {
        if (bookshelfId == null) {
            return RestStatus.INVALID_INPUT;
        }
        
        if (StringUtils.isBlank(isbn13)) {
            return RestStatus.INVALID_INPUT;
        }
        
        final Bookshelf bookshelf = bookshelfDao.find(bookshelfId);
        
        if (bookshelf == null) {
            return RestStatus.NOT_FOUND;
        }
        
        final Book book = bookDao.findByIsbn13(isbn13);
        
        if (book == null) {
            return RestStatus.NOT_FOUND;
        }
        
        bookshelf.getBooks().add(book);
        
        bookshelfDao.persist(bookshelf);
        
        return RestStatus.CREATED;
    }
    
    public Bookshelf getBookshelfById(Long bookshelfId) {
        if (bookshelfId == null) {
            return null;
        }
        
        return bookshelfDao.find(bookshelfId);
    }
    
    public Bookshelf getBookshelf(Long userId, String title) {
        if (userId == null) {
            return null;
        }
        
        if (StringUtils.isBlank(title)) {
            return null;
        }
        
        return bookshelfDao.getBookshelf(userId, title);
    }
    
    public RestStatus registerBookshelf(Long userId, String title) {
        if (userId == null) {
            return RestStatus.INVALID_INPUT;
        }
        
        if (StringUtils.isBlank(title)) {
            return RestStatus.INVALID_INPUT;
        }
        
        final BookshelfUser bookshelfUser = bookshelfUserDao.find(userId);
        
        if (bookshelfUser == null) {
            return RestStatus.NOT_FOUND;
        }
        
        final boolean userHasBookshelf = bookshelfDao.userHasBookshelf(userId, title);
        
        if (userHasBookshelf) {
            return RestStatus.CONFLICT;
        }
        
        final Bookshelf bookshelf = new Bookshelf();
        bookshelf.setTitle(title);
        bookshelf.setBookshelfUser(bookshelfUser);
        bookshelfDao.persist(bookshelf);
        
        return RestStatus.CREATED;
    }
    
}
