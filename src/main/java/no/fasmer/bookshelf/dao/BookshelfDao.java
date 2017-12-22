package no.fasmer.bookshelf.dao;

import java.util.List;
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
    
    public List<Bookshelf> getAllBookshelvesByUsername(String username) {
        return em.createNamedQuery("getAllBookshelvesByUsername")
                .setParameter("u", username)
                .getResultList();
    }
    
    public boolean userHasBookshelf(String username, String title) {
        final Bookshelf bookshelf = getBookshelf(username, title);
        return bookshelf != null;
    }
    
    public Bookshelf getBookshelf(String username, String title) {
        final List<Bookshelf> results = em.createNamedQuery("getBookshelfByUsernameAndTitle")
                .setParameter("u", username)
                .setParameter("t", title)
                .getResultList();
        
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }
        
        return null;
    }
    
}
