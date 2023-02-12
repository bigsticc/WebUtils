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