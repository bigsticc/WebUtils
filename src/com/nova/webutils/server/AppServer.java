package com.nova.webutils.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

import com.nova.webutils.http.HttpParser;
import com.nova.webutils.http.HttpRequest;
import com.nova.webutils.http.HttpResponse;
import com.nova.webutils.http.HttpStatus;
import com.nova.webutils.http.ResponseBuilder;

public class AppServer {
    Selector selector;
    ServerSocketChannel socket;

    Map<String, Application> appMap = new HashMap<>();

    public AppServer() throws IOException {
        selector = Selector.open();
        socket = ServerSocketChannel.open();
    }

    public void run(int port) throws IOException {
        socket.socket().bind(new InetSocketAddress(port), 10);
        socket.configureBlocking(false);
        int ops = socket.validOps();
        socket.register(selector, ops);
        while(true) {
            selector.select();
            for (SelectionKey key : selector.selectedKeys()) {
                if(key.isAcceptable()) handleAccept(socket, key);
                else if(key.isReadable()) handleRead(key);
            }
        }
    }

    private void handleAccept(ServerSocketChannel socket, SelectionKey key) throws IOException {
        SocketChannel client = socket.accept();
        client.configureBlocking(false);

        client.register(selector, SelectionKey.OP_READ);
    }
    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel)key.channel();

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        client.read(buffer);
        ByteArrayInputStream in = new ByteArrayInputStream(buffer.array());
        HttpRequest req = HttpParser.parseRequest(in);

        String res = HttpParser.serializeResponse(process(req));
        ByteBuffer out = ByteBuffer.wrap(res.getBytes());
        client.write(out);

        client.close();
    }

    private HttpResponse process(HttpRequest req) {
        String path = req.getUri().getRawPath();

        for(Map.Entry<String, Application> entry : appMap.entrySet()) {
            if(path.contains(entry.getKey())) return entry.getValue().process(req); 
        }
        return new ResponseBuilder()
        .version("HTTP/1.1")
        .status(HttpStatus.NOT_FOUND)
        .header("Content-Type", "text/html")
        .body("<html><h1>404 Not Found</h1></html>")
        .getResponse();
    }
}
