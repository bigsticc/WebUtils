package com.nova.webutils.http;

import java.util.Map;

public class HttpResponse {
    String version;
    HttpStatus status;
    Map<String, String> headers;
    String body;

    protected HttpResponse() {}
    protected HttpResponse(String version, HttpStatus status, Map<String, String> headers, String body) {
        this.version = version;
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public String getVersion() {
        return version;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public String getBody() {
        return body;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String toString() {
        StringBuilder response = new StringBuilder();
        response.append(version + " " + status.getStatus() + " " + status.getReasonPhrase() + "\r\n");
        for (Map.Entry<String, String> header : headers.entrySet()) {
            response.append(header.getKey() + ": " + header.getValue() + "\r\n");
        }
        response.append("\r\n");
        response.append(body);
        return response.toString();
    }
}
