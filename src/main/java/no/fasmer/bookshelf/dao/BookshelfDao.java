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
    
    public boolean userHasBookshelf(Long userId, String title) {
        final Bookshelf bookshelf = getBookshelf(userId, title);
        return bookshelf != null;
    }
    
    public Bookshelf getBookshelf(Long userId, String title) {
        return (Bookshelf) em.createNamedQuery("getBookshelf")
                .setParameter("userId", userId)
                .setParameter("title", title)
                .getSingleResult();
    }
    
}
