package com.nova.webutils.http;

/** Utility for making HttpResponse objects
 *  @author Supernova
 *  @since 1.0
 *  @see HttpResponse
 */
public class ResponseBuilder {
    HttpResponse response = new HttpResponse();

    public ResponseBuilder version(String version) {
        response.setVersion(version);
        return this;
    }
    public ResponseBuilder status(HttpStatus status) {
        response.setStatus(status);
        return this;
    }
    public ResponseBuilder header(String name, String value) {
        response.getHeaders().put(name, value);
        return this;
    }
    public ResponseBuilder body(String value) {
        this.header("Content-Length", Integer.toString(value.length()));
        response.setBody(value);
        return this;
    }

    public HttpResponse getResponse() {
        return response;
    }
}
