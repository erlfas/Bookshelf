package no.fasmer.bookshelf.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.dao.BookDao;
import no.fasmer.bookshelf.mapper.BookMapper;
import no.fasmer.bookshelf.enums.BookServiceStatus;

@Stateless
public class BookBean {
    
    @Inject
    private BookDao bookDao;
    
    @Inject
    private Logger logger;

    public BookServiceStatus addBook(no.fasmer.bookshelf.model.Book book) {
        try {
            final no.fasmer.bookshelf.entity.Book jpaBook = BookMapper.map(book);
            bookDao.persist(jpaBook);
            
            return BookServiceStatus.OK;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Book not persisted.", e);
            return BookServiceStatus.INTERNAL_ERROR;
        }
    }
    
}
