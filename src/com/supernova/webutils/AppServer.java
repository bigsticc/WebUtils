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

import com.supernova.webutils.http.HttpParser;
import com.supernova.webutils.http.HttpRequest;
import com.supernova.webutils.http.HttpResponse;
import com.supernova.webutils.http.HttpStatus;
import com.supernova.webutils.util.MessageHelper;

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
            while(!isInterrupted()) {
                selector.select();
                for(SelectionKey key : selector.selectedKeys()) {
                    if(key.isAcceptable()) handleAccept();
                    else if(key.isReadable()) handleRead(key);
                    
                    selector.selectedKeys().remove(key);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAccept() throws IOException {
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

        if(!appMap.containsKey(path)) return MessageHelper.resError(HttpStatus.NOT_FOUND);

        try {
            return appMap.entrySet().stream()
                    .filter(e -> path.matches(e.getKey())).map(Map.Entry::getValue)
                    .findFirst().orElseThrow(IllegalStateException::new)
                    .getConstructor().newInstance().process(req);
        } catch (Exception e) {
            e.printStackTrace();
            return MessageHelper.resError(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}