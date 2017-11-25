package no.fasmer.bookshelf.rest;

import no.fasmer.bookshelf.enums.BookServiceStatus;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import no.fasmer.bookshelf.api.BookApi;
import no.fasmer.bookshelf.ejb.BookBean;
import no.fasmer.bookshelf.model.Book;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class BookApiImpl implements BookApi {
    
    @Inject
    private BookBean bookBean;
    
    @Override
    public Response addBook(Book book, SecurityContext securityContext) {
        final BookServiceStatus bookServiceStatus = bookBean.addBook(book);
        return Response.status(bookServiceStatus.getStatus()).build();
    }

    @Override
    public Response deleteBook(String isbn13, String apiKey, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response getBookByIsbn13(String isbn13, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response updateBook(Book body, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response uploadFile(MultipartFormDataInput input, String isbn13, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}