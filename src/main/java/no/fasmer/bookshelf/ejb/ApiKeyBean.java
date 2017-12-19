package no.fasmer.bookshelf.ejb;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.dao.ApiKeyDao;
import no.fasmer.bookshelf.dao.BookshelfUserDao;
import no.fasmer.bookshelf.rest.dto.AuthenticatedUser;
import no.fasmer.bookshelf.entity.ApiKey;
import no.fasmer.bookshelf.entity.BookshelfUser;
import no.fasmer.bookshelf.entity.SecurityLevel;
import no.fasmer.bookshelf.utils.KeyBundle;
import no.fasmer.bookshelf.utils.KeyGenerator;
import no.fasmer.bookshelf.utils.PasswordHashGenerator;
import org.apache.commons.lang3.StringUtils;

@Stateless
public class ApiKeyBean {

    @Inject
    private Logger logger;

    @Inject
    private ApiKeyDao apiKeyDao;

    @Inject
    private BookshelfUserDao bookshelfUserDao;

    public AuthenticatedUser getUserToken(String username, String password) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Blank username");
        }

        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Blank password");
        }

        final BookshelfUser bookshelfUser = bookshelfUserDao.findByUsername(username);

        if (bookshelfUser == null) {
            throw new IllegalArgumentException("Invalid username/password");
        }

        final String hashedPassword = PasswordHashGenerator.generate(password, PasswordHashGenerator.DEFAULT_SALT);
        final ApiKey apiKey = apiKeyDao.findByUsernameAndPassword(username, hashedPassword);

        if (apiKey == null) {
            throw new IllegalArgumentException("Invalid username/password");
        }

        if (apiKey.getExpires().before(Date.from(Instant.now()))) {
            apiKeyDao.remove(apiKey);

            final ApiKey newApiKey = generateAndSaveNewApiKey(bookshelfUser);
            if (newApiKey != null) {
                return new AuthenticatedUser(username, newApiKey.getApiKey(), newApiKey.getExpires());
            }
        }

        return new AuthenticatedUser(username, hashedPassword, apiKey.getExpires());
    }

    public AuthenticatedUser issueUserToken(String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Blank username");
        }

        final BookshelfUser bookshelfUser = bookshelfUserDao.findByUsername(username);

        if (bookshelfUser == null) {
            throw new IllegalArgumentException("Invalid username");
        }

        final ApiKey newApiKey = generateAndSaveNewApiKey(bookshelfUser);
        if (newApiKey != null) {
            return new AuthenticatedUser(username, newApiKey.getApiKey(), newApiKey.getExpires());
        }

        throw new IllegalStateException("Could not create API key.");
    }

    public ApiKey generateAndSaveNewApiKey(BookshelfUser bookshelfUser) {
        int i = 0;
        while (i < 10) {
            final KeyBundle apiKeyBundle = KeyGenerator.generate();
            final ApiKey apiKey = apiKeyDao.find(apiKeyBundle.getKey());

            if (apiKey == null) {
                final LocalDateTime expires = LocalDateTime.now().plusDays(1);
                final Instant expiresInstant = expires.atZone(ZoneId.systemDefault()).toInstant();
                final Date expiresDate = Date.from(expiresInstant);

                final ApiKey newApiKey = new ApiKey();
                newApiKey.setApiKey(apiKeyBundle.getKey());
                newApiKey.setExpires(expiresDate);
                newApiKey.setBookshelfUser(bookshelfUser);

                apiKeyDao.persist(newApiKey);

                return newApiKey;
            }
            i++;
        }

        return null;
    }

    public boolean isValidAdmin(String apiKeyStr) {
        if (StringUtils.isBlank(apiKeyStr)) {
            logger.warning("Blank apiKey");
            return false;
        }

        if (apiKeyStr.length() != 32) {
            return false;
        }

        final KeyBundle keyBundle = KeyGenerator.extract(apiKeyStr);
        final String hashedPassword = PasswordHashGenerator.generate(keyBundle.getPassword(), keyBundle.getSalt());
        final ApiKey apiKey = apiKeyDao.find(hashedPassword);

        if (apiKey == null) {
            return false;
        }

        final BookshelfUser bookshelfUser = apiKey.getBookshelfUser();

        if (bookshelfUser == null) {
            return false;
        }

        return bookshelfUser.getSecurityLevel() == SecurityLevel.ADMIN && notExpired(apiKey);
    }

    public boolean isValidUser(String apiKeyStr) {
        if (StringUtils.isBlank(apiKeyStr)) {
            logger.warning("Blank apiKey");
            return false;
        }

        if (apiKeyStr.length() != 32) {
            return false;
        }

        final KeyBundle keyBundle = KeyGenerator.extract(apiKeyStr);
        final String hashedPassword = PasswordHashGenerator.generate(keyBundle.getPassword(), keyBundle.getSalt());
        final ApiKey apiKey = apiKeyDao.find(hashedPassword);

        if (apiKey == null) {
            return false;
        }

        final BookshelfUser bookshelfUser = apiKey.getBookshelfUser();

        if (bookshelfUser == null) {
            return false;
        }

        final SecurityLevel securityLevel = bookshelfUser.getSecurityLevel();

        return (securityLevel == SecurityLevel.USER || securityLevel == SecurityLevel.ADMIN) && notExpired(apiKey);
    }

    public boolean isValid(String apiKeyStr) {
        if (StringUtils.isBlank(apiKeyStr)) {
            logger.warning("Blank apiKey");
            return false;
        }

        if (apiKeyStr.length() != 32) {
            return false;
        }

        final KeyBundle keyBundle = KeyGenerator.extract(apiKeyStr);
        final String hashedPassword = PasswordHashGenerator.generate(keyBundle.getPassword(), keyBundle.getSalt());
        final ApiKey apiKey = apiKeyDao.find(hashedPassword);

        if (apiKey == null) {
            return false;
        }

        return notExpired(apiKey);
    }

    public BookshelfUser getBookshelfUser(String apiKeyStr) {
        if (StringUtils.isBlank(apiKeyStr)) {
            logger.warning("Blank apiKey");
            return null;
        }

        if (apiKeyStr.length() != 32) {
            return null;
        }

        final KeyBundle keyBundle = KeyGenerator.extract(apiKeyStr);
        final String hashedPassword = PasswordHashGenerator.generate(keyBundle.getPassword(), keyBundle.getSalt());
        final ApiKey apiKey = apiKeyDao.find(hashedPassword);

        if (apiKey == null) {
            return null;
        }

        return apiKey.getBookshelfUser();
    }

    private boolean notExpired(ApiKey apiKey) {
        final Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        return apiKey.getExpires().after(now);
    }

}
