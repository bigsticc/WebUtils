package com.supernova.webutils.http;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/** Programmatic representation of a client's request to a server.
 *  @author Supernova
 *  @since 1.0
 */
public class HttpRequest {
    HttpMethod method;
    URI uri;
    String version;

    Map<String, String> headers = new HashMap<>();
    String body;
    
    protected HttpRequest() {}
    protected HttpRequest(HttpMethod method, URI uri, String version, Map<String, String> headers, String body) {
        this.method = method;
        this.uri = uri;
        this.version = version;
        this.headers = headers;
        this.body = body;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public URI getUri() {
        return uri;
    }

    public String getVersion() {
        return version;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }


    protected void setMethod(HttpMethod method) {
        this.method = method;
    }
    protected void setUri(URI uri) {
        this.uri = uri;
    }
    protected void setVersion(String version) {
        this.version = version;
    }
    protected void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    protected void setBody(String body) {
        this.body = body;
    }


}
