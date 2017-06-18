package cai.peter.challenge.webserver.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by peter on 17/06/17.
 */
public class DefaultHttpResponse implements HttpResponse {

    private OutputStream outputStream;

    public DefaultHttpResponse(final OutputStream outputStream) {
         this.outputStream = outputStream;
    }

    @Override
    public int getStatusCode() {
        return 0;
    }

    @Override
    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        this.outputStream.write(bytes);
    }

    public void sendback(final HttpResponseStatus status, final String body) throws IOException {
        final String responseStatusLine = buildResponseStatusLine(status);
        write(responseStatusLine.getBytes());

        //TODO write other response headers here
        // not supporting other response headers is one of limits of this implementation

        write("Content-Type: text/html\r\n".getBytes());
        //the last response header and then send response body back
        if(body != null && body.length() > 0) {
            write(("Content-Length:" + body.length() + "\r\n\r\n").getBytes());
            write(body.getBytes());
        } else {
            write(("Content-Length: 0\r\n\r\n").getBytes());
        }
        this.outputStream.flush();
        this.outputStream.close();
    }

    private String buildResponseStatusLine(final HttpResponseStatus status) {
        return "HTTP/1.1 " + status.toString() + "\r\n";
    }
}
