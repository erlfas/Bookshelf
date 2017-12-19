package no.fasmer.bookshelf.rest;

import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import no.fasmer.bookshelf.api.UserApi;
import no.fasmer.bookshelf.dao.BookshelfUserDao;
import no.fasmer.bookshelf.ejb.ApiKeyBean;
import no.fasmer.bookshelf.ejb.BookshelfUserBean;
import no.fasmer.bookshelf.entity.BookshelfUser;
import no.fasmer.bookshelf.mapper.Mapper;
import no.fasmer.bookshelf.model.User;
import no.fasmer.bookshelf.rest.dto.AuthenticatedUser;
import no.fasmer.bookshelf.rest.enums.RestStatus;
import org.apache.commons.lang3.StringUtils;

public class UserApiImpl implements UserApi {

    @Inject
    private ApiKeyBean apiKeyBean;

    @Inject
    private BookshelfUserBean bookshelfUserBean;

    @Inject
    private BookshelfUserDao bookshelfUserDao;

    @Inject
    private Logger logger;

    @Override
    public Response registerUser(User user, SecurityContext securityContext) {
        logger.info("registerUser: start");
        
        final BookshelfUser bookshelfUser = Mapper.mapUser(user);
        final RestStatus restStatus = bookshelfUserBean.save(bookshelfUser);

        if (restStatus == RestStatus.CREATED) {
            final AuthenticatedUser authenticatedUser = apiKeyBean.issueUserToken(user.getUsername());
            return Response.ok(Mapper.mapAuthenticatedUser(authenticatedUser)).build();
        }

        return Response.status(restStatus.getCode()).build();
    }

    @Override
    public Response getUser(String apiKey, String username, SecurityContext securityContext) {
        // TODO
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
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {

            logger.info(String.format("loginUser: " + username));

            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            final AuthenticatedUser authenticatedUser = apiKeyBean.getUserToken(username, password);

            // Return the token on the response
            return Response.ok(authenticatedUser).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @Override
    public Response logoutUser(SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
        final boolean isValid = bookshelfUserDao.isValidUserCredentials(username, password);
        if (!isValid) {
            throw new IllegalStateException("Not valid credentials");
        }
    }

}
