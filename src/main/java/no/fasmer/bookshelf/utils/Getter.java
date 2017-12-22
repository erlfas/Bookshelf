package no.fasmer.bookshelf.utils;

import no.fasmer.bookshelf.entity.Bookshelf;

public class Getter {
    
    public final static String BOOKSHELF_URL = "/bookshelfcontent/%s";
    
    public static String getBookshelfUrl(Bookshelf bookshelf) {
        return String.format(BOOKSHELF_URL, getBookshelfId(bookshelf));
    }

    public static String getBookshelfId(Bookshelf bookshelf) {
        if (bookshelf == null || bookshelf.getId() == null) {
            return "";
        }
        
        return bookshelf.getId().toString();
    }
    
}
