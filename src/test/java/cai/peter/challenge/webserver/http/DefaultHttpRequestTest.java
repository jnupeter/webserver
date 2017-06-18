package cai.peter.challenge.webserver.http;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by peter on 18/06/17.
 */
public class DefaultHttpRequestTest {

    @Test
    public void shouldBeAbleToParseRequestCorrectly() throws IOException {
        final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("correct-request");
        DefaultHttpRequest request = DefaultHttpRequest.build(inputStream);

        assertEquals("/abcd", request.getRequestPath());
        assertEquals(HttpMethod.GET, request.getMethod());
        assertNull(request.getHeaders());
    }

    @Test
    public void notSupportedRequestCouldBeParsedCorrectly() throws IOException {
        final InputStream inputStream = getClass().getClassLoader().getResourceAsStream("not-supported-request");
        DefaultHttpRequest request = DefaultHttpRequest.build(inputStream);

        assertEquals("/fake-path", request.getRequestPath());
        assertEquals(HttpMethod.POST, request.getMethod());
        assertNull(request.getHeaders());
    }
}
