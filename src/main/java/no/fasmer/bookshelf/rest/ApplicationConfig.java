package no.fasmer.bookshelf.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        resources.add(no.fasmer.bookshelf.rest.BookApiImpl.class);
        resources.add(no.fasmer.bookshelf.rest.BookshelfApiImpl.class);
        resources.add(no.fasmer.bookshelf.rest.UserApiImpl.class);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(no.fasmer.bookshelf.rest.AuthenticationFilter.class);
    }
    
}
