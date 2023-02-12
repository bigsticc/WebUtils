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
import com.supernova.webutils.http.HttpResponse;
import com.supernova.webutils.http.RequestBuilder;
import com.supernova.webutils.util.MessageHelper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    public void exampleTest() throws IOException {
        HttpResponse res = HttpClient.request(new InetSocketAddress("example.com", 80), new RequestBuilder()
            .method(HttpMethod.GET)
            .path(URI.create("/"))
            .version("HTTP/1.1")
            .header("Host", "example.com")
            .getRequest());
        assertNotNull(res);
        System.out.println(HttpParser.serializeResponse(res));
    }
    @Test
    public void getTest() throws IOException {
        String host = "example.com";
        HttpResponse res = HttpClient.request(new InetSocketAddress(host, 80), MessageHelper.getPage(host, "/"));
        assertNotNull(res);
        System.out.println(HttpParser.serializeResponse(res));
    }
}
