package no.fasmer.bookshelf.rest.dto;

import no.fasmer.bookshelf.entity.Book;
import no.fasmer.bookshelf.rest.enums.RestStatus;

public class BookResponse {
    
    private final RestStatus bookServiceStatus;
    private final Book book;

    public BookResponse(RestStatus bookServiceStatus, Book book) {
        this.bookServiceStatus = bookServiceStatus;
        this.book = book;
    }

    public RestStatus getBookServiceStatus() {
        return bookServiceStatus;
    }

    public Book getBook() {
        return book;
    }
    
}
