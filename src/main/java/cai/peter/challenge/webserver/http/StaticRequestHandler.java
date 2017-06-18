package cai.peter.challenge.webserver.http;

import cai.peter.challenge.webserver.context.ServerContext;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

/**
 * The implementation for RequestHandler, which serve static contect from a root directory specified from
 * Configuration#getRoot().
 *
 * ServerContext was passed in, so that critical components like CacheManager could be available during the processing
 * of a request.
 *
 * Created by peter on 17/06/17.
 */
public class StaticRequestHandler implements RequestHandler {

    private static final Logger LOGGER = Logger.getLogger(StaticRequestHandler.class.getName());

    private String root;
    private ServerContext context;

    public StaticRequestHandler(final String root, final ServerContext context) {
        this.root = root;
        this.context = context;
    }

    @Override
    public void processRequest(final HttpRequest req, final HttpResponse res) throws IOException {

        final DefaultHttpRequest request = (DefaultHttpRequest) req;
        final DefaultHttpResponse response = (DefaultHttpResponse) res;

        if(request.getMethod() == null || request.getRequestPath() == null) { //safeguard
            LOGGER.finest("Receiving bad request.");
            return;
        }
        LOGGER.info("request (method filename): " + request.getMethod() + " " + request.getRequestPath());

        if (HttpMethod.GET != request.getMethod()) { //not supporting any HttpMethod other than GET
            response.sendback(HttpResponseStatus.METHOD_NOT_ALLOWED, "Only Supports GET.");
            return;
        }

        // to check if this request has been served before
        String cacheKey = request.getMethod() + " " + request.getRequestPath();
        if (context.getCacheManager().getCache(cacheKey) != null) { //serve from cache
            LOGGER.finest("Serve from cache, the request is: " + cacheKey);
            response.sendback(HttpResponseStatus.OK, context.getCacheManager().getCache(cacheKey).toString());
            return;
        }

        File rootDirectory = new File(root);
        final File fileToBeServed = new File(rootDirectory.getAbsoluteFile() + request.getRequestPath());
        if(!fileToBeServed.exists()) {
            response.sendback(HttpResponseStatus.NOT_FOUND, "File " + request.getRequestPath() + " not found.");
            return;
        }

        try {
            String content = buildContentPage(fileToBeServed);
            context.getCacheManager().setCache(cacheKey, context);
            response.sendback(HttpResponseStatus.OK, content);
        } catch (IOException ioe) { //errors occurs when reading files
            LOGGER.severe("Error occurs when reading file: " + fileToBeServed.getName() + ", reason:" + ioe.getMessage());
            response.sendback(HttpResponseStatus.INTERNAL_SERVER_ERROR, "Error occurs when reading file: " + request.getRequestPath());
        }
    }

    private String buildContentPage(final File file) throws IOException {
        final StringBuilder sb = new StringBuilder("<html><body>");

        if(file.isFile()) {
            try{
                final BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                    sb.append("<br/>");
                }
            } catch(FileNotFoundException fnfe) {
                //DO nothing, because we've already make sure the file exists
            }
        }

        if(file.isDirectory()) {
            Arrays.stream(file.listFiles()).forEach(f -> sb.append(present(f)));
        }

        sb.append("</body></html>");
        return sb.toString();
    }

    private String present(File file) {
        if (file.isDirectory()) {
            return String.format("directory: <a href=\"./%s/\">%s</a></br>", file.getName(), file.getName());
        } else {
            return String.format("file: <a href=\"./%s\">%s</a>, size: %d KB, lastModified: %s </br>",
                                 file.getName(),
                                 file.getName(),
                                 Math.round(file.length() / 1024) + 1,
                                 new Date(file.lastModified()));
        }
    }
}
