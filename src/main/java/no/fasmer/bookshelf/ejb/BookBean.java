package no.fasmer.bookshelf.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.dao.BookDao;
import no.fasmer.bookshelf.entity.Book;
import no.fasmer.bookshelf.mapper.Mapper;
import no.fasmer.bookshelf.enums.BookServiceStatus;
import org.apache.commons.lang3.StringUtils;

@Stateless
public class BookBean {

    @Inject
    private BookDao bookDao;

    @Inject
    private Logger logger;
    
    public Book getBook(String isbn13) {
        if (StringUtils.isBlank(isbn13)) {
            return null;
        }
        
        return bookDao.findByIsbn13(isbn13);
    }

    public BookServiceStatus addBook(no.fasmer.bookshelf.model.Book book) {
        try {
            final no.fasmer.bookshelf.entity.Book jpaBook = Mapper.map(book);
            bookDao.persist(jpaBook);

            return BookServiceStatus.OK;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Book not persisted.", e);
            return BookServiceStatus.INTERNAL_ERROR;
        }
    }

    public BookServiceStatus deleteBook(final String isbn13) {
        if (StringUtils.isBlank(isbn13)) {
            return BookServiceStatus.INVALID_INPUT;
        }

        final no.fasmer.bookshelf.entity.Book book = bookDao.findByIsbn13(isbn13);

        if (book == null) {
            return BookServiceStatus.NOT_FOUND;
        }

        try {
            bookDao.remove(book);
            return BookServiceStatus.OK;
        } catch (Exception e) {
            return BookServiceStatus.INTERNAL_ERROR;
        }

    }

}
