package no.fasmer.bookshelf.rest;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import no.fasmer.bookshelf.rest.enums.RestStatus;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import no.fasmer.bookshelf.api.BookApi;
import no.fasmer.bookshelf.ejb.ApiKeyBean;
import no.fasmer.bookshelf.ejb.BookBean;
import no.fasmer.bookshelf.mapper.Mapper;
import no.fasmer.bookshelf.model.Book;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class BookApiImpl implements BookApi {
    
    @Inject
    private BookBean bookBean;
    
    @Inject
    private ApiKeyBean apiKeyBean;
    
    @Inject
    private Logger logger;

    @Override
    public Response addBook(String apiKey, Book book, SecurityContext securityContext) {
        if (!apiKeyBean.isValid(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final RestStatus restStatus;
        try {
            restStatus = bookBean.addBook(Mapper.map(book));
        } catch (ParseException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        switch (restStatus) {
            case CREATED:
                return Response
                .status(restStatus.getCode())
                .header("Location", "/book/" + book.getIsbn13())
                .build();
            default:
                return Response
                        .status(restStatus.getCode())
                        .build();
        }
    }

    @Override
    public Response deleteBook(String apiKey, String isbn13, SecurityContext securityContext) {
        if (!apiKeyBean.isValidAdmin(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final RestStatus restStatus = bookBean.deleteBook(isbn13);
        
        return Response.status(restStatus.getCode()).build();
    }

    @Override
    public Response getBookByIsbn13(String isbn13, SecurityContext securityContext) {
        final no.fasmer.bookshelf.model.Book book = bookBean.getBook(isbn13);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(book).build();
    }

    @Override
    public Response updateBook(String apiKey, String isbn13, Book book, SecurityContext securityContext) {
        if (!apiKeyBean.isValid(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final RestStatus restStatus;
        try {
            restStatus = bookBean.updateBook(Mapper.map(book));
        } catch (ParseException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        return Response.status(restStatus.getCode()).build();
    }

    @Override
    public Response uploadFile(MultipartFormDataInput input, String apiKey, String isbn13, SecurityContext securityContext) {
        if (!apiKeyBean.isValid(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

    @Override
    public Response getBookByTitle(String apiKey, String title, SecurityContext securityContext) {
        logger.log(Level.INFO, String.format("getBookByTitle"));
        
        try {
            final List<no.fasmer.bookshelf.model.Book> books = bookBean.findBooksByTitle(title);
            return Response.ok(books).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}