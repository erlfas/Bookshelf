package no.fasmer.bookshelf.dao;

import java.util.List;
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
    
    public Book findByIsbn13(final String isbn13) {
        final List<Book> books = em.createNamedQuery("findByIsbn13")
                .setParameter("isbn13", isbn13)
                .getResultList();
        
        if (books != null && !books.isEmpty()) {
            return books.get(0);
        }
        
        return null;
    }
    
}
