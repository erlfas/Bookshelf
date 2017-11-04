package no.fasmer.bookshelf.dao;

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
    
}