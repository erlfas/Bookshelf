package no.fasmer.bookshelf.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import no.fasmer.bookshelf.dao.BookDao;
import no.fasmer.bookshelf.entity.Book;
import static no.fasmer.bookshelf.utils.ExceptionUtils.getRootErrorMessage;

@Model
public class BookService {
    
    @Inject
    private FacesContext facesContext;
    
    @Produces
    @Named
    private Book newBook;
    
    @Inject
    private BookDao bookDao;
    
    @Inject
    private List<Book> books; // cf. BookProducer
    
    @Inject
    private Event<Book> newBookEvent;
    
    @PostConstruct
    public void initNewBook() {
        newBook = new Book();
    }
    
    public void addNewBook() {
        try {
            bookDao.persist(newBook);
            
            final FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Done!", "Book created");
            facesContext.addMessage(null, m);
            initNewBook();
            newBookEvent.fire(newBook);
        } catch (Exception e) {
            final String errorMessage = getRootErrorMessage(e);
            final FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Error while saving data");
            facesContext.addMessage(null, m);
        }
    }
    
}
