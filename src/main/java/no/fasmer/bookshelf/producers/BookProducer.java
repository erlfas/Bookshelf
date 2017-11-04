package no.fasmer.bookshelf.producers;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import no.fasmer.bookshelf.dao.BookDao;
import no.fasmer.bookshelf.entity.Book;

@RequestScoped
public class BookProducer {
    
    @Inject
    private BookDao bookDao;
    
    private List<Book> books;
    
    @PostConstruct
    public void retrieveAllBooks() {
        books = bookDao.findAll();
    }
    
    @Produces
    @Named
    public List<Book> getBooks() {
        return books;
    }
    
    public void onListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Book member) {
        retrieveAllBooks();
    }
    
}
