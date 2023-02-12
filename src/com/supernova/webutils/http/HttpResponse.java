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

import java.util.HashMap;
import java.util.Map;

/** Programmatic representation of a server's response to a client
 *  @author Supernova
 *  @since 1.0
 */
public class HttpResponse {
    String version;
    HttpStatus status;
    Map<String, String> headers = new HashMap<>();
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
        response.append(version).append(" ").append(status.getStatus()).append(" ").append(status.getReasonPhrase()).append("\r\n");
        for (Map.Entry<String, String> header : headers.entrySet()) {
            response.append(header.getKey()).append(": ").append(header.getValue()).append("\r\n");
        }
        response.append("\r\n");
        response.append(body);
        return response.toString();
    }
}
