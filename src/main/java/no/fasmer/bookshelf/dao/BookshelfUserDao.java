package no.fasmer.bookshelf.dao;

import java.util.List;
import java.util.logging.Level;
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
        try {
            final List<BookshelfUser> results = (List<BookshelfUser>) em.createNamedQuery("findBookshelfUserByUsername")
                    .setParameter("un", username)
                    .getResultList();
            
            if (results != null && !results.isEmpty()) {
                return results.get(0);
            }

            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e, () -> "Could not find user.");
            return null;
        }
    }

    public boolean isValidUserCredentials(String username, String password) {
        try {
            final List<BookshelfUser> results = (List<BookshelfUser>) em.createNamedQuery("findBookshelfUser")
                    .setParameter("un", username)
                    .setParameter("pwd", PasswordHashGenerator.generate(password))
                    .getResultList();

            return results != null && !results.isEmpty();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e, () -> "Could not find user.");
            return false;
        }
    }

}
