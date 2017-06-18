package cai.peter.challenge.webserver;

/**
 * An exception that indicates errors while server starting up
 * Created by peter on 17/06/17.
 */
public class ServerException extends Exception {
    public ServerException() {
        super();
    }

    public ServerException(final String message) {
        super(message);
    }
}
