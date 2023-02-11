package com.nova.webutils;

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
import com.nova.webutils.util.MessageHelper;

/** Main entry point to a WebUtils system, receives http requests
 *  and delegates them to user written applications
 *  @author Supernova
 *  @since 1.0
 */

public class AppServer extends Thread {
    Selector selector;
    ServerSocketChannel socket;

    Map<String, Class<? extends Application>> appMap = new HashMap<>();

    /** Creates a new AppServer and binds it to the system ip address, on the specified port
     *
     * @param port Desired port to open on (0-65535)
     * @throws IOException When there is an error in NIO operations
     */
    public AppServer(int port) throws IOException {
        selector = Selector.open();
        socket = ServerSocketChannel.open();
        socket.socket().bind(new InetSocketAddress(port), 10);
    }

    /**
     * Registers an Application to search for when a request is made
     * @param path App will respond when request is made to this path
     * @param app Application to register
     */
    public void registerApp(String path, Class<? extends Application> app) {
        appMap.put(path, app);
    }

    public void run() {
        try {
            socket.configureBlocking(false);
            int ops = socket.validOps();
            socket.register(selector, ops);
            while(true) {
                selector.select();
                for (SelectionKey key : selector.selectedKeys()) {
                    if(key.isAcceptable()) handleAccept(socket);
                    else if(key.isReadable()) handleRead(key);
                    
                    selector.selectedKeys().remove(key);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAccept(ServerSocketChannel socket) throws IOException {
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

        if(appMap.containsKey(path)) {
            try {
                return appMap.get(path).getConstructor().newInstance().process(req);
            } catch (Exception e) {
                e.printStackTrace();
                return MessageHelper.resError(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return MessageHelper.resError(HttpStatus.NOT_FOUND);
    }
}