package com.nova.webutils.client;

import com.nova.webutils.http.HttpParser;
import com.nova.webutils.http.HttpResponse;
import com.nova.webutils.http.HttpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
public class HttpClient {
    Socket socket;

    public HttpClient() {
        socket = new Socket();
        try{
            socket.bind(null);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public HttpResponse request(InetSocketAddress host, HttpRequest req) throws IOException {
        socket.connect(host);
        socket.getOutputStream().write(HttpParser.serializeRequest(req).getBytes());
        HttpResponse res = HttpParser.parseResponse(socket.getInputStream());

        socket.close();
        return res;
    }
}
