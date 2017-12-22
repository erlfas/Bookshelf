package no.fasmer.bookshelf.rest;

import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import no.fasmer.bookshelf.api.BookshelfApi;
import no.fasmer.bookshelf.ejb.ApiKeyBean;
import no.fasmer.bookshelf.ejb.BookshelfBean;
import no.fasmer.bookshelf.entity.Bookshelf;
import no.fasmer.bookshelf.entity.BookshelfUser;
import no.fasmer.bookshelf.mapper.Mapper;
import no.fasmer.bookshelf.model.BookToAdd;
import no.fasmer.bookshelf.model.BookshelfToAdd;
import no.fasmer.bookshelf.rest.enums.RestStatus;
import org.apache.commons.lang3.StringUtils;

public class BookshelfApiImpl implements BookshelfApi {
    
    @Inject
    private BookshelfBean bookshelfBean;

    @Inject
    private ApiKeyBean apiKeyBean;
    
    @Inject
    private Logger logger;

    @Override
    public Response addBookToBookshelf(String apiKey, BookToAdd bookToAdd, SecurityContext securityContext) {
        logger.log(Level.INFO, "addBookToBookshelf");
        
        if (bookToAdd == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (StringUtils.isBlank(bookToAdd.getBookshelfTitle())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (StringUtils.isBlank(bookToAdd.getUsername())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (bookToAdd.getBook() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        final BookshelfUser bookshelfUser = apiKeyBean.getBookshelfUser(apiKey);
        
        if (bookshelfUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        if (!bookshelfUser.getUsername().equals(bookToAdd.getUsername())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final no.fasmer.bookshelf.entity.Book book = Mapper.map(bookToAdd.getBook());
        
        final RestStatus restStatus = bookshelfBean.addBookToBookshelf(bookToAdd.getUsername(), bookToAdd.getBookshelfTitle(), book);

        return Response.status(restStatus.getCode()).build();
    }

    @Override
    public Response addBookshelf(String apiKey, BookshelfToAdd bookshelfToAdd, SecurityContext securityContext) {
        logger.log(Level.INFO, "addBookshelf");
        
        if (bookshelfToAdd == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (StringUtils.isBlank(bookshelfToAdd.getTitle())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (StringUtils.isBlank(bookshelfToAdd.getUsername())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        final BookshelfUser bookshelfUser = apiKeyBean.getBookshelfUser(apiKey);
        
        if (bookshelfUser == null) {
            logger.log(Level.WARNING, String.format("Cound not find user %s with API-KEY %s.", bookshelfToAdd.getUsername(), apiKey));
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        if (!bookshelfUser.getUsername().equals(bookshelfToAdd.getUsername())) {
            logger.log(Level.WARNING, String.format("Usernames (%s and %s) don't match.", bookshelfToAdd.getUsername(), bookshelfToAdd.getUsername()));
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        final RestStatus restStatus = bookshelfBean.addBookshelf(bookshelfToAdd.getUsername(), bookshelfToAdd.getTitle());
        
        if (restStatus == RestStatus.CREATED) {
            return Response
                .created(URI.create(String.format("/bookshelf?title=%s&username=%s", bookshelfToAdd.getTitle(), bookshelfToAdd.getUsername())))
                .build();
        }

        return Response.status(restStatus.getCode()).build();
    }

    @Override
    public Response getBookshelf(String apiKey, String title, String username, SecurityContext securityContext) {
        if (StringUtils.isBlank(apiKey)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (StringUtils.isBlank(title)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (StringUtils.isBlank(username)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        final BookshelfUser bookshelfUser = apiKeyBean.getBookshelfUser(apiKey);
        
        if (bookshelfUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        if (!bookshelfUser.getUsername().equals(username)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final Bookshelf bookshelf = bookshelfBean.getBookshelf(username, title);
        
        if (bookshelf == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(Mapper.map(bookshelf, String.format("/bookshelf?title=%s&username=%s", title, username))).build();
    }

    @Override
    public Response getBookshelfs(String apiKey, String username, SecurityContext securityContext) {
        if (StringUtils.isBlank(apiKey)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (StringUtils.isBlank(username)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        final BookshelfUser bookshelfUser = apiKeyBean.getBookshelfUser(apiKey);
        
        if (bookshelfUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        if (!bookshelfUser.getUsername().equals(username)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final List<Bookshelf> bookshelves = bookshelfBean.getAllBookshelves(username);
        
        return Response.ok(Mapper.map(bookshelves, "/bookshelf?title=%s&username=%s")).build();
    }

}
