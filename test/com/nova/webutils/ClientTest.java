package com.nova.webutils;

import com.nova.webutils.http.HttpMethod;
import com.nova.webutils.http.HttpParser;
import com.nova.webutils.http.HttpResponse;
import com.nova.webutils.http.RequestBuilder;
import com.nova.webutils.util.MessageHelper;
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
