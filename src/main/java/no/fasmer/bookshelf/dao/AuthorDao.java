package no.fasmer.bookshelf.dao;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.entity.Author;

@Stateless
public class AuthorDao extends AbstractDao<Author> {

    @Inject
    private Logger logger;
    
    public AuthorDao() {
        super(Author.class);
    }
    
    public Author findByName(String firstName, String lastName) {
        final List<Author> list = em.createNamedQuery("findByName")
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getResultList();
        
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        
        return null;
    }
    
}