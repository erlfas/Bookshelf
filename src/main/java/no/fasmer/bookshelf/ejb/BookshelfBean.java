package no.fasmer.bookshelf.ejb;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.dao.BookshelfDao;

@Stateless
public class BookshelfBean {
    
    @Inject
    private BookshelfDao bookshelfDao;
    
    @Inject
    private Logger logger;
    
}
