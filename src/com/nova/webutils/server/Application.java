package com.nova.webutils.server;

import com.nova.webutils.http.HttpRequest;
import com.nova.webutils.http.HttpResponse;

public interface Application {
    public HttpResponse process(HttpRequest req);
}