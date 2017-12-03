package no.fasmer.bookshelf.enums;

public enum BookServiceStatus {

    OK(200),
    INVALID_INPUT(400),
    NOT_FOUND(404),
    INTERNAL_ERROR(500);

    private final int status;
    
    private BookServiceStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
    
}
