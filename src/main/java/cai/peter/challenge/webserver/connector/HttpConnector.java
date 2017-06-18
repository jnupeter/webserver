package cai.peter.challenge.webserver.connector;

/**
 * Created by peter on 17/06/17.
 */
public class HttpConnector implements Connector {

    @Override
    public boolean support(ConnectorType type) {
        return ConnectorType.HTTP == type;
    }
}
