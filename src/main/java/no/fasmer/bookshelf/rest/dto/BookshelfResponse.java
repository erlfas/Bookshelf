package no.fasmer.bookshelf.rest.dto;

import no.fasmer.bookshelf.entity.Bookshelf;
import no.fasmer.bookshelf.rest.enums.RestStatus;

public class BookshelfResponse {

    private final RestStatus bookServiceStatus;
    private final Bookshelf bookshelf;

    public BookshelfResponse(RestStatus bookServiceStatus, Bookshelf bookshelf) {
        this.bookServiceStatus = bookServiceStatus;
        this.bookshelf = bookshelf;
    }

    public RestStatus getBookServiceStatus() {
        return bookServiceStatus;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }
    
}
