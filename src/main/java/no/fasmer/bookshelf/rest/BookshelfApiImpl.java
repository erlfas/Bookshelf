package no.fasmer.bookshelf.rest;

import java.net.URI;
import java.text.ParseException;
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
import no.fasmer.bookshelf.model.Book;
import no.fasmer.bookshelf.model.BookshelfToAdd;
import no.fasmer.bookshelf.rest.dto.BookshelfResponse;
import no.fasmer.bookshelf.rest.enums.RestStatus;
import no.fasmer.bookshelf.utils.Getter;
import org.apache.commons.lang3.StringUtils;

public class BookshelfApiImpl implements BookshelfApi {
    
    @Inject
    private BookshelfBean bookshelfBean;

    @Inject
    private ApiKeyBean apiKeyBean;
    
    @Inject
    private Logger logger;

    @Override
    public Response addBookToBookshelf(String apiKey, String id, Book book, SecurityContext securityContext) {
        logger.log(Level.INFO, "addBookToBookshelf");
        
        if (book == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (StringUtils.isBlank(id)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        Long idLong = null;
        try {
            idLong = Long.parseLong(id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        final BookshelfUser bookshelfUserOfApiKey = apiKeyBean.getBookshelfUser(apiKey);
        
        if (bookshelfUserOfApiKey == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final Bookshelf bookshelf = bookshelfBean.getBookshelfById(idLong);
        
        if (bookshelf == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        final BookshelfUser bookshelfUserOfBookshelf = bookshelf.getBookshelfUser();
        
        if (bookshelfUserOfBookshelf == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        if (!bookshelfUserOfBookshelf.getUsername().equals(bookshelfUserOfApiKey.getUsername())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final BookshelfResponse bookshelfResponse;
        try {
            bookshelfResponse = bookshelfBean.addBookToBookshelf(idLong, Mapper.map(book));
        } catch (ParseException ex) {
            // book.published
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (bookshelfResponse.getRestStatus() == RestStatus.CREATED) {
            logger.log(Level.INFO, "addBookToBookshelf: book added to bookshelf");
            return Response
                .created(URI.create(Getter.getBookshelfUrl(bookshelfResponse.getBookshelf())))
                .build();
        }

        return Response.status(bookshelfResponse.getRestStatus().getCode()).build();
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

        final BookshelfResponse bookshelfResponse = bookshelfBean.addBookshelf(bookshelfToAdd.getUsername(), bookshelfToAdd.getTitle());
        
        if (bookshelfResponse.getRestStatus() == RestStatus.CREATED) {
            return Response.ok(Mapper.map(bookshelfResponse.getBookshelf())).build();
        }

        return Response.status(bookshelfResponse.getRestStatus().getCode()).build();
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
        
        return Response.ok(Mapper.map(bookshelf)).build();
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
        
        return Response.ok(Mapper.map(bookshelves)).build();
    }

    @Override
    public Response getBookshelfById(String apiKey, String id, SecurityContext securityContext) {
        logger.info("getBookshelfById: intro");
        
        if (StringUtils.isBlank(id)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        try {
            Long.parseLong(id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        if (StringUtils.isBlank(apiKey)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        final BookshelfUser userOfApiKey = apiKeyBean.getBookshelfUser(apiKey);
        
        if (userOfApiKey == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final Bookshelf bookshelf = bookshelfBean.getBookshelfById(Long.parseLong(id));
        
        if (bookshelf == null || bookshelf.getBookshelfUser() == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        final BookshelfUser userOfBookshelf = bookshelf.getBookshelfUser();
        
        if (!userOfBookshelf.getUsername().equals(userOfApiKey.getUsername())) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        
        logger.info(String.format("getBookshelfById: found bookshelf with %d books.", bookshelf.getBooks().size()));
        
        return Response.ok(Mapper.map(bookshelf)).build();
    }

}
