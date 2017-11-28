package no.fasmer.bookshelf.dao;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.entity.BookshelfUser;
import no.fasmer.bookshelf.utils.PasswordHashGenerator;

@Stateless
public class BookshelfUserDao extends AbstractDao<BookshelfUser> {
    
    @Inject
    private Logger logger;
    
    public BookshelfUserDao() {
        super(BookshelfUser.class);
    }
    
    public BookshelfUser findByUsername(String username) {
        final BookshelfUser bookshelfUser = (BookshelfUser) em.createNamedQuery("findBookshelfUserByUsername")
                .setParameter("un", username)
                .getSingleResult();
        
        return bookshelfUser;
    }
    
    public boolean isValidUserCredentials(String username, String password) {
        final BookshelfUser bookshelfUser = (BookshelfUser) em.createNamedQuery("findBookshelfUser")
                .setParameter("un", username)
                .setParameter("pwd", PasswordHashGenerator.generate(password))
                .getSingleResult();
        
        return bookshelfUser != null;
    }
    
}
