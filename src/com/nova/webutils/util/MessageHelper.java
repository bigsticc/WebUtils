package com.nova.webutils.util;

import com.nova.webutils.http.*;

import java.net.URI;

/** Utility methods to create http requests using common templates
 * @author Supernova
 * @since 1.0
 */
public class MessageHelper {
    public static HttpRequest getPage(String host, String path) {
        return new RequestBuilder()
                .method(HttpMethod.GET)
                .path(URI.create(path))
                .version("HTTP/1.1")
                .header("Host", host)
                .getRequest();
    }
    public static HttpResponse resError(HttpStatus status) {
        return new ResponseBuilder()
                .version("HTTP/1.1")
                .status(status)
                .header("Content-Type", "text/html")
                .body("<html><h1>%d%s</h1><hr></html>".formatted(status.getStatus(), status.getReasonPhrase()))
                .getResponse();
    }
    public static HttpResponse quickHtml(String html) {
        return new ResponseBuilder()
                .version("HTTP/1.1")
                .status(HttpStatus.OK)
                .header("Content-Type", "text/html")
                .body(html)
                .getResponse();
    }
}
