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

package com.supernova.webutils.util;

import com.supernova.webutils.http.*;

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
