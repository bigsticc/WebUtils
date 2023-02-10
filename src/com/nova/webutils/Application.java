package com.nova.webutils;

import com.nova.webutils.http.HttpRequest;
import com.nova.webutils.http.HttpResponse;

/** User written function for processing http requests
 *  @author Supernova
 *  @since 1.0
 */
public interface Application {
    /** Executed by the server when an implementing class is invoked by the server
     *
     * @param req The request given by the client
     * @return A response for the client's request
     */
    HttpResponse process(HttpRequest req) throws Exception;
}