package no.fasmer.bookshelf.ejb;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.dao.ApiKeyDao;
import no.fasmer.bookshelf.entity.ApiKey;
import no.fasmer.bookshelf.entity.SecurityLevel;
import org.apache.commons.lang3.StringUtils;

@Stateless
public class ApiKeyBean {

    @Inject
    private Logger logger;

    @Inject
    private ApiKeyDao apiKeyDao;

    public boolean isValidAdmin(String apiKeyStr) {
        if (StringUtils.isBlank(apiKeyStr)) {
            logger.warning("Blank apiKey");
            return false;
        }

        final ApiKey apiKey = apiKeyDao.find(apiKeyStr);

        if (apiKey == null) {
            return false;
        }

        return apiKey.getSecurityLevel() == SecurityLevel.ADMIN && notExpired(apiKey);
    }

    public boolean isValidUser(String apiKeyStr) {
        if (StringUtils.isBlank(apiKeyStr)) {
            logger.warning("Blank apiKey");
            return false;
        }

        final ApiKey apiKey = apiKeyDao.find(apiKeyStr);

        if (apiKey == null) {
            return false;
        }

        final SecurityLevel securityLevel = apiKey.getSecurityLevel();

        return (securityLevel == SecurityLevel.USER || securityLevel == SecurityLevel.ADMIN) && notExpired(apiKey);
    }

    public boolean isValid(String apiKeyStr) {
        if (StringUtils.isBlank(apiKeyStr)) {
            logger.warning("Blank apiKey");
            return false;
        }

        final ApiKey apiKey = apiKeyDao.find(apiKeyStr);

        if (apiKey == null) {
            return false;
        }

        return notExpired(apiKey);
    }

    private boolean notExpired(ApiKey apiKey) {
        final Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        return apiKey.getExpires().after(now);
    }

}
