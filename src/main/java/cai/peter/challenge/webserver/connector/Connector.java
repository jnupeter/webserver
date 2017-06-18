package cai.peter.challenge.webserver.connector;

/**
 * Define the protocols this webserver uses to connect to the outside world.
 * Currently only HTTPConnector is implemented.
 *
 * Created by peter on 17/06/17.
 */
public interface Connector {
    boolean support(ConnectorType type);
}
