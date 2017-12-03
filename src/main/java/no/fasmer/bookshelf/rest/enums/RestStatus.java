package no.fasmer.bookshelf.rest.enums;

public enum RestStatus {

    OK(200),
    CREATED(201),
    INVALID_INPUT(400),
    NOT_FOUND(404),
    CONFLICT(409),
    INTERNAL_ERROR(500);

    private final int code;
    
    private RestStatus(int status) {
        this.code = status;
    }

    public int getCode() {
        return code;
    }
    
}
