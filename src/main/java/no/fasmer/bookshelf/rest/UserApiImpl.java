package no.fasmer.bookshelf.rest;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import no.fasmer.bookshelf.api.UserApi;
import no.fasmer.bookshelf.ejb.ApiKeyBean;
import no.fasmer.bookshelf.ejb.BookshelfUserBean;
import no.fasmer.bookshelf.entity.BookshelfUser;
import no.fasmer.bookshelf.mapper.Mapper;
import no.fasmer.bookshelf.model.User;
import no.fasmer.bookshelf.rest.enums.RestStatus;

public class UserApiImpl implements UserApi {
    
    @Inject
    private ApiKeyBean apiKeyBean;
    
    @Inject
    private BookshelfUserBean bookshelfUserBean;

    @Override
    public Response addUser(String apiKey, User user, SecurityContext securityContext) {
        if (!apiKeyBean.isValid(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final BookshelfUser bookshelfUser = Mapper.mapUser(user);
        final RestStatus restStatus = bookshelfUserBean.save(bookshelfUser);
        
        return Response.status(restStatus.getCode()).build();
    }

    @Override
    public Response getUser(String apiKey, String username, SecurityContext securityContext) {
        if (!apiKeyBean.isValid(apiKey)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        final BookshelfUser bookshelfUser = bookshelfUserBean.findByUsername(username);
        
        if (bookshelfUser != null) {
            return Response.ok(Mapper.mapBookshelfUser(bookshelfUser)).build();
        }
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response loginUser(String username, String password, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response logoutUser(SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
