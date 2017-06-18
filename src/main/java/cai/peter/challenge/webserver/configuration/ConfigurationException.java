package cai.peter.challenge.webserver.configuration;

/**
 * An exception that are used to indicated errors while configuring server.
 * Created by peter on 17/06/17.
 */
public class ConfigurationException extends Exception {
    public ConfigurationException() {
        super();
    }

    public ConfigurationException(final String message) {
        super(message);
    }
}
