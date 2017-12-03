package no.fasmer.bookshelf.dao;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.entity.Bookshelf;

@Stateless
public class BookshelfDao extends AbstractDao<Bookshelf> {
    
    @Inject
    private Logger logger;
    
    public BookshelfDao() {
        super(Bookshelf.class);
    }
    
}
