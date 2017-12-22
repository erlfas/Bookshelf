package no.fasmer.bookshelf.rest.dto;

import no.fasmer.bookshelf.entity.Bookshelf;
import no.fasmer.bookshelf.rest.enums.RestStatus;

public class BookshelfResponse {

    private final RestStatus restStatus;
    private final Bookshelf bookshelf;

    public BookshelfResponse(RestStatus bookServiceStatus, Bookshelf bookshelf) {
        this.restStatus = bookServiceStatus;
        this.bookshelf = bookshelf;
    }

    public RestStatus getRestStatus() {
        return restStatus;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }
    
}
