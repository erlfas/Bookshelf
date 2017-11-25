package no.fasmer.bookshelf.ejb;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.dao.ApiKeyDao;
import no.fasmer.bookshelf.entity.ApiKey;
import org.apache.commons.lang3.StringUtils;

@Stateless
public class ApiKeyBean {
    
    @Inject
    private Logger logger;
    
    @Inject
    private ApiKeyDao apiKeyDao;
    
    public boolean isValidAndNotExpired(String apiKeyStr) {
        if (StringUtils.isBlank(apiKeyStr)) {
            logger.warning("Blank apiKey");
            return false;
        }
        
        final ApiKey apiKey = apiKeyDao.find(apiKeyStr);
        
        if (apiKey == null) {
            return false;
        }
        
        final Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        
        return apiKey.getExpires().after(now);
    }
    
}
