package no.fasmer.bookshelf.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import no.fasmer.bookshelf.api.BookshelfApi;

public class BookshelfApiImpl implements BookshelfApi {

    @Override
    public Response addBookToBookshelf(String API_KEY, String isbn13, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response addBookshelf(String API_KEY, String userId, String title, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response getBookshelf(String API_KEY, String bookshelfId, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
