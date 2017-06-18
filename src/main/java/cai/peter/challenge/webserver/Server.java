package cai.peter.challenge.webserver;

import cai.peter.challenge.webserver.configuration.Configuration;
import cai.peter.challenge.webserver.configuration.PropertyFileConfiguration;
import cai.peter.challenge.webserver.context.DefaultServerContext;
import cai.peter.challenge.webserver.http.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 *  Bootstrap class for the Simple Web Server. It loads server configuration and then bootstrap server.
 *
 * Created by peter on 17/06/17.
 */
public final class Server {

    private static Logger LOGGER = Logger.getLogger(Server.class.getName());

    public Server() {

    }

    private void serve(Configuration configuration) throws ServerException {
        LOGGER.info("Starting the server...");
        LOGGER.info("Serving content from " + configuration.getRoot());

        //init server context
        final DefaultServerContext context = new DefaultServerContext();

        try {
            final ServerSocket serverSocket = new ServerSocket(configuration.getPort());
            while(true) {
                final Socket socket = serverSocket.accept();
                final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                final DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                final HttpRequest request = DefaultHttpRequest.build(socket.getInputStream());
                final HttpResponse response = new DefaultHttpResponse(socket.getOutputStream());

                final RequestHandler requestHandler = new StaticRequestHandler(configuration.getRoot(), context);
                requestHandler.processRequest(request, response);
            }
        } catch (IOException ioe) {
            throw new ServerException(ioe.getMessage());
        }
    }

    public static void main(String[] args) {
        Configuration configuration = new PropertyFileConfiguration("./config.properties");
        Server server = new Server();
        try {
            server.serve(configuration);
        } catch (ServerException se) {
            LOGGER.severe("Server failed:" + se.getMessage());
        }
    }
}
