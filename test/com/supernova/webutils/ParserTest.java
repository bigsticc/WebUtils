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
import com.supernova.webutils.http.HttpMethod;
import com.supernova.webutils.http.HttpParser;
import com.supernova.webutils.http.HttpRequest;
import com.supernova.webutils.http.HttpResponse;
import org.junit.jupiter.api.Test;

import com.supernova.webutils.http.HttpStatus;
import com.supernova.webutils.http.RequestBuilder;
import com.supernova.webutils.http.ResponseBuilder;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;

public class ParserTest {
    @Test
    public void testReqParse() throws IOException {
        String req = """
        POST /path/to/resource HTTP/1.1
        Host: www.example.com
        Content-Type: application/json
        Content-Length: 32

        {"name": "John Smith","age": 30}
        """;

        HttpRequest request = HttpParser.parseRequest(new ByteArrayInputStream(req.getBytes()));

        HttpRequest correct = new RequestBuilder()
            .method(HttpMethod.POST)
            .path(URI.create("/path/to/resource"))
            .version("HTTP/1.1")
            .header("Host", "www.example.com")
            .header("Content-Type", "application/json")
            .body("{\"name\": \"John Smith\",\"age\": 30}")
            .getRequest();
        
        String reqStr = HttpParser.serializeRequest(request);
        String testStr = HttpParser.serializeRequest(correct);
        assertEquals(reqStr, testStr);
    }

    @Test
    public void testResParse() throws IOException {
        String res = """
        HTTP/1.1 200 OK
        Content-Type: application/json
        Content-Length: 32

        {"name": "John Smith","age": 30}
        """;

        HttpResponse response = HttpParser.parseResponse(new ByteArrayInputStream(res.getBytes()));

        HttpResponse correct = new ResponseBuilder()
        .version("HTTP/1.1")
        .status(HttpStatus.OK)
        .header("Content-Type", "application/json")
        .body("{\"name\": \"John Smith\",\"age\": 30}")
        .getResponse();

        String resStr = HttpParser.serializeResponse(response);
        String testStr = HttpParser.serializeResponse(correct);
        assertEquals(resStr, testStr);
    }
}
