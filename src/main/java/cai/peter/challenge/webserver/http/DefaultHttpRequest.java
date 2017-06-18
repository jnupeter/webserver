package cai.peter.challenge.webserver.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

/**
 * Created by peter on 17/06/17.
 */
public class DefaultHttpRequest implements HttpRequest {

    private HttpMethod method;
    private Map<String, String> headers;
    private String requestPath;


    private DefaultHttpRequest() {
    }

    public static DefaultHttpRequest build(final InputStream inputStream) throws IOException {
        return parseRequest(inputStream);
    }

    public void setMethod(final HttpMethod method) {
        this.method = method;
    }

    public void setHeaders(final Map<String, String> headers) {
        this.headers = headers;
    }

    public void setRequestPath(final String requestPath) {
        this.requestPath = requestPath;
    }

    @Override
    public HttpMethod getMethod() {
        return this.method;
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String getRequestPath() {
        return requestPath;
    }

    //util
    private static DefaultHttpRequest parseRequest(final InputStream inputStream) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Optional<String> firstLine = Optional.ofNullable(reader.readLine());

        final DefaultHttpRequest request = new DefaultHttpRequest();
        if(firstLine.isPresent()) {
            final StringTokenizer stringTokenizer = new StringTokenizer(firstLine.get());
            final Optional<String> method = Optional.ofNullable(stringTokenizer.nextToken());   // get HttpMethod
            method.ifPresent(m -> request.setMethod(HttpMethod.valueOf(m.toUpperCase())));

            final Optional<String> filename = Optional.ofNullable(stringTokenizer.nextToken());
            filename.ifPresent(fn -> request.setRequestPath(fn));
        }

        //TODO build request header from input stream, not in this implementation for this challenge

        return request;
    }
}
