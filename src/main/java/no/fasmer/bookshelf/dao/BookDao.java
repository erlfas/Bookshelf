package no.fasmer.bookshelf.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.entity.Book;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

@Stateless
public class BookDao extends AbstractDao<Book> {
    
    @Inject
    private Logger logger;
    
    public BookDao() {
        super(Book.class);
    }
    
    public List<Book> findByTitle(String title) {
        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
        final Query query = queryBuilder.keyword().onField("title").matching(title).createQuery();
        final List<Book> books = fullTextEntityManager.createFullTextQuery(query).getResultList();
        
        if (books == null || books.isEmpty()) {
            logger.log(Level.INFO, String.format("BookDao: findByTitle (%s): found no books.", title));
        } else {
            logger.log(Level.INFO, String.format("BookDao: findByTitle (%s): found %d books.", title, books.size()));
        }
        
        return books;
    }
    
    public Book findByIsbn13(String isbn13) {
        final List<Book> books = em.createNamedQuery("findByIsbn13")
                .setParameter("isbn13", isbn13)
                .getResultList();
        
        if (books != null && !books.isEmpty()) {
            return books.get(0);
        }
        
        return null;
    }
    
}
