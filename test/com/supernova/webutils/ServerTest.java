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
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import com.supernova.webutils.http.*;
import com.supernova.webutils.util.MessageHelper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ServerTest {
    @Test
    public void pathTest() throws IOException {
        AppServer server = new AppServer(8080);

        server.registerApp("/", TestApp.class);
        server.registerApp("/secret", PathApp.class);
        server.start();

        InetSocketAddress addr = new InetSocketAddress("localhost", 8080);

        HttpResponse res = HttpClient.request(addr, new RequestBuilder()
                .method(HttpMethod.GET)
                .path(URI.create("/"))
                .version("HTTP/1.1")
                .header("Host", "localhost")
                .getRequest()
        );
        HttpResponse res2 = HttpClient.request(addr, new RequestBuilder()
                .method(HttpMethod.GET)
                .path(URI.create("/secret"))
                .version("HTTP/1.1")
                .header("Host", "localhost")
                .getRequest()
        );

        assertNotNull(res);
        assertNotNull(res2);
        System.out.println(HttpParser.serializeResponse(res));
        System.out.println(HttpParser.serializeResponse(res2));
    }

    public static class TestApp implements Application {
        public TestApp() {}
        @Override
        public HttpResponse process(HttpRequest req) {
            return MessageHelper.quickHtml("<html><h1>Hello World!</h1></html>");
        }
    }

    public static class PathApp implements Application {
        public PathApp() {}

        @Override
        public HttpResponse process(HttpRequest req) {
            return MessageHelper.quickHtml("<html><p>you have accessed the top secret page.</p></html>");
        }
    }
}
