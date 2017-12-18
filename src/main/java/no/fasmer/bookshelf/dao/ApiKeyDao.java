package no.fasmer.bookshelf.dao;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import no.fasmer.bookshelf.entity.ApiKey;

@Stateless
public class ApiKeyDao extends AbstractDao<ApiKey> {
    
    @Inject
    private Logger logger;
    
    public ApiKeyDao() {
        super(ApiKey.class);
    }
    
    public ApiKey find(String apiKey) {
        final List<ApiKey> results = em.createNamedQuery("findApiKey")
                .setParameter("ak", apiKey)
                .getResultList();
        
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }
        
        return null;
    }
    
    public ApiKey findByUsernameAndApiKey(String apiKey, String username) {
        final List<ApiKey> results = em.createNamedQuery("findApiKey")
                .setParameter("ak", apiKey)
                .setParameter("un", username)
                .getResultList();
        
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        }
        
        return null;
    }
    
}
