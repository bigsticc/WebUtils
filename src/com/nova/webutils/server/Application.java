package com.nova.webutils.server;

import com.nova.webutils.http.HttpRequest;
import com.nova.webutils.http.HttpResponse;

/** User written function for processing http requests
 *  @author Supernova
 *  @since 1.0
 */
public interface Application {
    public HttpResponse process(HttpRequest req);
}