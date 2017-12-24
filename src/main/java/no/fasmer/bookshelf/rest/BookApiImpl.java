package no.fasmer.bookshelf.rest;

import no.fasmer.bookshelf.rest.enums.RestStatus;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import no.fasmer.bookshelf.api.BookApi;
import no.fasmer.bookshelf.ejb.ApiKeyBean;
import no.fasmer.bookshelf.ejb.BookBean;
import no.fasmer.bookshelf.mapper.Mapper;
import no.fasmer.bookshelf.model.Book;
import no.fasmer.bookshelf.rest.dto.BookResponse;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class BookApiImpl implements BookApi {
    
    @Inject
    private BookBean bookBean;
    
    @Inject
    private ApiKeyBean apiKeyBean;

    @Override
    public Response addBook(String apiKey, Book book, SecurityContext securityContext) {
        if (!apiKeyBean.isValid(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final BookResponse bookResponse = bookBean.addBook(Mapper.map(book));
        
        switch (bookResponse.getBookServiceStatus()) {
            case CREATED:
                return Response
                .status(bookResponse.getBookServiceStatus().getCode())
                .header("Location", "/book/" + book.getIsbn13())
                .build();
            default:
                return Response
                        .status(bookResponse.getBookServiceStatus().getCode())
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
        final no.fasmer.bookshelf.entity.Book book = bookBean.getBook(isbn13);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(Mapper.map(book)).build();
    }

    @Override
    public Response updateBook(String apiKey, String isbn13, Book book, SecurityContext securityContext) {
        if (!apiKeyBean.isValid(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final RestStatus restStatus = bookBean.updateBook(Mapper.map(book));
        
        return Response.status(restStatus.getCode()).build();
    }

    @Override
    public Response uploadFile(MultipartFormDataInput input, String apiKey, String isbn13, SecurityContext securityContext) {
        if (!apiKeyBean.isValid(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
    
}