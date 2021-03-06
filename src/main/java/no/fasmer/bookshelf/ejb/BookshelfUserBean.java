package no.fasmer.bookshelf.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.dao.BookshelfUserDao;
import no.fasmer.bookshelf.entity.BookshelfUser;
import no.fasmer.bookshelf.rest.enums.RestStatus;

@Stateless
public class BookshelfUserBean {
    
    @Inject
    private BookshelfUserDao bookshelfUserDao;
    
    @Inject
    private Logger logger;
    
    public RestStatus save(BookshelfUser bookshelfUser) {
        try {
            final BookshelfUser existingUser = findByUsername(bookshelfUser.getUsername());
            if (existingUser != null) {
                return RestStatus.CONFLICT;
            }
            
            bookshelfUserDao.persist(bookshelfUser);
            return RestStatus.CREATED;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error persisting user.");
            return RestStatus.INTERNAL_ERROR;
        }
    }
    
    public BookshelfUser findByUsername(String username) {
        return bookshelfUserDao.findByUsername(username);
    }
    
}
