package no.fasmer.bookshelf.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import no.fasmer.bookshelf.handler.BookApiService;
import no.fasmer.bookshelf.handler.NotFoundException;
import no.fasmer.bookshelf.model.Book;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class BookApiServiceImpl extends BookApiService {

    @Override
    public Response addBook(Book body, SecurityContext securityContext) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response deleteBook(String isbn13, String apiKey, SecurityContext securityContext) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response getBookByIsbn13(String isbn13, SecurityContext securityContext) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response updateBook(Book body, SecurityContext securityContext) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response uploadFile(MultipartFormDataInput input, String isbn13, SecurityContext securityContext) throws NotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
