package cai.peter.challenge.webserver.http;

import java.io.IOException;

/**
 * The extension point of the WebServer. By providing different implementation of the is interface, could extend
 * the web server's behavior, for example, to support dynamic content etc.,
 *
 * In this challenge, only one implementation, i.e., @see StaticRequestHandler is provided, which is supposed to
 * serve static content.
 *
 * Created by peter on 17/06/17.
 */
public interface RequestHandler {
    void processRequest(HttpRequest request, HttpResponse response) throws IOException;
}
