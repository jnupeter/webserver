package cai.peter.challenge.webserver.configuration;

import cai.peter.challenge.webserver.connector.ConnectorType;

import java.net.SocketAddress;

/**
 * Server configuration abstraction. It provides the extensibility to load different sources for configuration.
 * In this demo, only a config.properties file based configuration is considered.
 *
 * For further extension, XML file based, annotation based configuration could be implemented.
 *
 * Created by peter on 17/06/17.
 */
public interface Configuration {
    int getPort();
    SocketAddress bindAddress();
    ConnectorType connector();
    String getRoot();
}
