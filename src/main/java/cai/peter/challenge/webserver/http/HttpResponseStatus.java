package cai.peter.challenge.webserver.http;

/**
 * Created by peter on 18/06/17.
 */
public enum HttpResponseStatus {
    OK(200, "OK"),

    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String description;

    HttpResponseStatus(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String toString() {
        return this.code + " " + this.description;
    }
}
