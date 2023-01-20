package com.nova.webutils.http;

import java.net.URI;

public class RequestBuilder {
    HttpRequest request = new HttpRequest();

    public RequestBuilder method(HttpMethod method) {
        request.setMethod(method);
        return this;
    }
    public RequestBuilder path(URI path) {
        request.setUri(path);
        return this;
    }
    public RequestBuilder version(String version) {
        request.setVersion(version);
        return this;
    }
    public RequestBuilder header(String name, String value) {
        request.getHeaders().put(name, value);
        return this;
    }
    public RequestBuilder body(String body) {
        request.setBody(body);
        return this;
    }

    public HttpRequest getRequest() {
        return request;
    }
}
