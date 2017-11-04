package no.fasmer.bookshelf.dao;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.entity.Book;

@Stateless
public class BookDao extends AbstractDao<Book> {
    
    @Inject
    private Logger logger;
    
    public BookDao() {
        super(Book.class);
    }
    
}
