package no.fasmer.bookshelf.dao;

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
        return (ApiKey) em.createNamedQuery("findApiKey")
                .setParameter("ak", apiKey)
                .getSingleResult();
    }
    
}
