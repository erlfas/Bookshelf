package no.fasmer.bookshelf.rest;

import no.fasmer.bookshelf.enums.BookServiceStatus;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import no.fasmer.bookshelf.api.BookApi;
import no.fasmer.bookshelf.ejb.ApiKeyBean;
import no.fasmer.bookshelf.ejb.BookBean;
import no.fasmer.bookshelf.model.Book;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class BookApiImpl implements BookApi {
    
    @Inject
    private BookBean bookBean;
    
    @Inject
    private ApiKeyBean apiKeyBean;

    @Override
    public Response addBook(String apiKey, Book book, SecurityContext securityContext) {
        if (!apiKeyBean.isValidAndNotExpired(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final BookServiceStatus bookServiceStatus = bookBean.addBook(book);
        return Response.status(bookServiceStatus.getStatus()).build();
    }

    @Override
    public Response deleteBook(String apiKey, String isbn13, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response getBookByIsbn13(String isbn13, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response updateBook(String apiKey, Book body, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response uploadFile(MultipartFormDataInput input, String apiKey, String isbn13, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}