package no.fasmer.bookshelf.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import no.fasmer.bookshelf.dao.BookshelfUserDao;
import no.fasmer.bookshelf.ejb.ApiKeyBean;
import no.fasmer.bookshelf.utils.KeyBundle;

@Path("/authentication")
public class AuthenticationEndpoint {
    
    @Inject
    private BookshelfUserDao bookshelfUserDao;
    
    @Inject
    private ApiKeyBean apiKeyBean;
    
    @POST
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(@QueryParam("username") String username, @QueryParam("password") String password) {
        try {

            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            final String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("/form")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUserByFormData(@FormParam("username") String username, @FormParam("password") String password) {
        try {

            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            final String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
        final boolean isValid = bookshelfUserDao.isValidUserCredentials(username, password);
        if (!isValid) {
            throw new IllegalStateException("Not valid credentials");
        }
    }

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        final KeyBundle keyBundle = apiKeyBean.issueUserToken(username);
        return keyBundle.getKey();
    }
    
}