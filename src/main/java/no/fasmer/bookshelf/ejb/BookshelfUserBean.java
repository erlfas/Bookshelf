package no.fasmer.bookshelf.ejb;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.dao.BookshelfUserDao;

@Stateless
public class BookshelfUserBean {
    
    @Inject
    private BookshelfUserDao bookshelfUserDao;
    
    @Inject
    private Logger logger;
    
}
