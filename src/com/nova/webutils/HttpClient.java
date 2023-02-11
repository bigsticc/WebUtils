package com.nova.webutils;

import com.nova.webutils.http.*;

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
