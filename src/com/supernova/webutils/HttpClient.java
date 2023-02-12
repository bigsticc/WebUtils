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

import com.supernova.webutils.http.HttpParser;
import com.supernova.webutils.http.HttpRequest;
import com.supernova.webutils.http.HttpResponse;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/** Basic HTTP client. Has basic static methods for assisting in making requests using the existing framework
 * @author Supernova
 * @since 1.0
 */
public class HttpClient {
    /** Makes a request to the specified host
     *
     * @param host Valid InetAddress which corresponds to the desired host
     * @param req HttpRequest object
     * @return the HttpResponse object which was returned by the host
     * @throws IOException when there is an error in the Socket connection
     */
    public static HttpResponse request(InetSocketAddress host, HttpRequest req) throws IOException {
        Socket socket = new Socket();
        socket.bind(null);
        socket.connect(host);
        socket.getOutputStream().write(HttpParser.serializeRequest(req).getBytes());
        HttpResponse res = HttpParser.parseResponse(socket.getInputStream());

        socket.close();
        return res;
    }
}
