package no.fasmer.bookshelf.mapper;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Mapper {
    
    public static List<no.fasmer.bookshelf.model.Tag> mapTags(Collection<no.fasmer.bookshelf.entity.Tag> jpaTags) {
        final List<no.fasmer.bookshelf.model.Tag> swaggerTags = new ArrayList<>();
        for (no.fasmer.bookshelf.entity.Tag jpaTag : jpaTags) {
            swaggerTags.add(mapTag(jpaTag));
        }
        
        return swaggerTags;
    }
    
    public static no.fasmer.bookshelf.model.Tag mapTag(no.fasmer.bookshelf.entity.Tag jpaTag) {
        final no.fasmer.bookshelf.model.Tag swaggerTag = new no.fasmer.bookshelf.model.Tag();
        swaggerTag.setName(jpaTag.getName());
        
        return swaggerTag;
    }
    
    public static List<no.fasmer.bookshelf.model.Author> mapAuthors(Collection<no.fasmer.bookshelf.entity.Author> jpaAuthors) {
        final List<no.fasmer.bookshelf.model.Author> swaggerAuthors = new ArrayList<>();
        for (no.fasmer.bookshelf.entity.Author jpaAuthor : jpaAuthors) {
            swaggerAuthors.add(mapAuthor(jpaAuthor));
        }
        
        return swaggerAuthors;
    }
    
    public static no.fasmer.bookshelf.model.Author mapAuthor(no.fasmer.bookshelf.entity.Author jpaAuthor) {
        final no.fasmer.bookshelf.model.Author swaggerAuthor = new no.fasmer.bookshelf.model.Author();
        swaggerAuthor.setId(jpaAuthor.getId());
        swaggerAuthor.setFirstName(jpaAuthor.getFirstName());
        swaggerAuthor.setLastName(jpaAuthor.getLastName());
        
        return swaggerAuthor;
    }
    
    public static no.fasmer.bookshelf.model.Book map(no.fasmer.bookshelf.entity.Book jpaBook) {
        final no.fasmer.bookshelf.model.Book swaggerBook = new no.fasmer.bookshelf.model.Book();
        swaggerBook.setAuthors(mapAuthors(jpaBook.getAuthors()));
        swaggerBook.setEdition(jpaBook.getEdition());
        swaggerBook.setIsbn10(jpaBook.getIsbn10());
        swaggerBook.setIsbn13(jpaBook.getIsbn13());
        swaggerBook.setNumPages(jpaBook.getNumPages());
        swaggerBook.setPublished(jpaBook.getPublished().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        swaggerBook.setPublisher(jpaBook.getPublisher());
        swaggerBook.setTags(mapTags(jpaBook.getTags()));
        swaggerBook.setTitle(jpaBook.getTitle());
        
        return swaggerBook;
    }

    public static no.fasmer.bookshelf.entity.Book map(no.fasmer.bookshelf.model.Book swaggerBook) {
        final no.fasmer.bookshelf.entity.Book jpaBook = new no.fasmer.bookshelf.entity.Book();
        
        final List<no.fasmer.bookshelf.entity.Author> jpaAuthors = new ArrayList<>();
        for (no.fasmer.bookshelf.model.Author swaggerAuthor : swaggerBook.getAuthors()) {
            final no.fasmer.bookshelf.entity.Author jpaAuthor = new no.fasmer.bookshelf.entity.Author();
            jpaAuthor.setFirstName(swaggerAuthor.getFirstName());
            jpaAuthor.setLastName(swaggerAuthor.getLastName());
            jpaAuthors.add(jpaAuthor);
        }
        jpaBook.setAuthors(jpaAuthors);
        
        jpaBook.setEdition(swaggerBook.getEdition());
        jpaBook.setIsbn10(swaggerBook.getIsbn10());
        jpaBook.setIsbn13(swaggerBook.getIsbn13());
        jpaBook.setNumPages(swaggerBook.getNumPages());
        jpaBook.setPublished(Date.from(swaggerBook.getPublished().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        jpaBook.setPublisher(swaggerBook.getPublisher());
        
        final List<no.fasmer.bookshelf.entity.Tag> jpaTags = new ArrayList<>();
        for (no.fasmer.bookshelf.model.Tag swaggerTag : swaggerBook.getTags()) {
            final no.fasmer.bookshelf.entity.Tag jpaTag = new no.fasmer.bookshelf.entity.Tag();
            jpaTag.setName(swaggerTag.getName());
            jpaTags.add(jpaTag);
        }
        jpaBook.setTags(jpaTags);
        
        jpaBook.setTitle(swaggerBook.getTitle());
        
        return jpaBook;
    }
    
}
