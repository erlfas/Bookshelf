package no.fasmer.bookshelf.mapper;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookMapper {

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
