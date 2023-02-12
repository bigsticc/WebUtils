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

package com.supernova.webutils;

import com.supernova.webutils.http.HttpRequest;
import com.supernova.webutils.http.HttpResponse;

/**
 * The `Application` interface represents an HTTP-based application that can process HTTP requests and produce HTTP responses.
 *
 * @author Supernova
 * @since 1.0
 */
public interface Application {
    /**
     * Processes an HTTP request and produces an HTTP response.
     *
     * @param req The HTTP request to be processed.
     * @return The HTTP response produced by processing the request.
     * @throws Exception If an error occurs while processing the request.
     */
    HttpResponse process(HttpRequest req) throws Exception;
}
