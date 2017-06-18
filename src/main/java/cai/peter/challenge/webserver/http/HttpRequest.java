package cai.peter.challenge.webserver.http;

import cai.peter.challenge.webserver.http.HttpMethod;

import java.util.Map;

/**
 * Created by peter on 17/06/17.
 */
public interface HttpRequest {
    HttpMethod getMethod();
    Map<String, String> getHeaders();
    String getRequestPath();
}
