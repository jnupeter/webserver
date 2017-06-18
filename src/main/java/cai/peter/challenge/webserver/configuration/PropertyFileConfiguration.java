package cai.peter.challenge.webserver.configuration;

import cai.peter.challenge.webserver.connector.ConnectorType;

import java.io.File;
import java.io.IOException;
import java.net.SocketAddress;

/**
 * The properties file implementation of Configuration interface.
 *
 * Created by peter on 17/06/17.
 */
public class PropertyFileConfiguration implements Configuration {


    public PropertyFileConfiguration(final String fileName) {
        //TODO just demonstrate this is the one of the configuration sources,
        //not actually loading configuration from file in this challenge.
    }

    @Override
    public int getPort() {
        return 8080;
    }

    @Override
    public SocketAddress bindAddress() {
        return null;
    }

    @Override
    public ConnectorType connector() {
        return ConnectorType.HTTP;
    }

    @Override
    public String getRoot() {
        String root = ".";
        try {
            root = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
}
