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
