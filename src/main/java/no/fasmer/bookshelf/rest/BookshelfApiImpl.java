package no.fasmer.bookshelf.rest;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import no.fasmer.bookshelf.api.BookshelfApi;
import no.fasmer.bookshelf.ejb.ApiKeyBean;
import no.fasmer.bookshelf.ejb.BookshelfBean;
import no.fasmer.bookshelf.entity.Bookshelf;
import no.fasmer.bookshelf.mapper.Mapper;
import no.fasmer.bookshelf.rest.enums.RestStatus;

public class BookshelfApiImpl implements BookshelfApi {

    @Inject
    private BookshelfBean bookshelfBean;

    @Inject
    private ApiKeyBean apiKeyBean;

    @Override
    public Response addBookToBookshelf(String apiKey, Long bookshelfId, String isbn13, SecurityContext securityContext) {
        if (!apiKeyBean.isValid(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final RestStatus restStatus = bookshelfBean.addBookToBookshelf(bookshelfId, isbn13);

        return Response
                .status(restStatus.getCode())
                .build();
    }

    @Override
    public Response addBookshelf(String apiKey, Long userId, String title, SecurityContext securityContext) {
        if (!apiKeyBean.isValid(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        final RestStatus restStatus = bookshelfBean.registerBookshelf(userId, title);

        return Response
                .status(restStatus.getCode())
                .build();
    }

    @Override
    public Response getBookshelf(String apiKey, Long bookshelfId, SecurityContext securityContext) {
        if (!apiKeyBean.isValid(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final Bookshelf bookshelf = bookshelfBean.getBookshelfById(bookshelfId);
        
        if (bookshelf == null) {
            return Response
                .status(Response.Status.NOT_FOUND)
                .build();
        }
        
        return Response.ok(Mapper.map(bookshelf)).build();
    }

}
