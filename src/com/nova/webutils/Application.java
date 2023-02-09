package com.nova.webutils;

import com.nova.webutils.http.HttpRequest;
import com.nova.webutils.http.HttpResponse;

/** User written function for processing http requests
 *  @author Supernova
 *  @since 1.0
 */
public interface Application {
    HttpResponse process(HttpRequest req);
}