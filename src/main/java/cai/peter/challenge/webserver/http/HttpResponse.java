package cai.peter.challenge.webserver.http;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by peter on 17/06/17.
 */
public interface HttpResponse {
    int getStatusCode();
    OutputStream getOutputStream();
    void write(byte[] bytes) throws IOException;
}
