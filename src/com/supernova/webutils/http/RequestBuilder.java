/*
 *  Copyright 2023 supernova
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.supernova.webutils.http;

import java.net.URI;

/** Utility for making HttpRequest objects
 *  @author Supernova
 *  @since 1.0
 *  @see HttpRequest
 */
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
        this.header("Content-Length", Integer.toString(body.length()));
        request.setBody(body);
        return this;
    }

    public HttpRequest getRequest() {
        return request;
    }
}
