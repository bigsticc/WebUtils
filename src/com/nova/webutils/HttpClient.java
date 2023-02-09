package com.nova.webutils;

import com.nova.webutils.http.HttpParser;
import com.nova.webutils.http.HttpResponse;
import com.nova.webutils.http.HttpRequest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
public class HttpClient {

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
