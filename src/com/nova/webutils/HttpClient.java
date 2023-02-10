package com.nova.webutils;

import com.nova.webutils.http.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;

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

    /** Helper method for making GET requests, this method simply invokes request() with a pre-built HttpRequest
     *
     * @param host Valid InetAddress which corresponds to the desired host
     * @param path Path to request to
     * @return the HttpResponse object which was returned by the host
     * @throws IOException when there is an error in the Socket connection
     */
    public static HttpResponse quickGet(InetSocketAddress host, String path) throws IOException {
        return request(host, new RequestBuilder()
                .method(HttpMethod.GET)
                .path(URI.create(path))
                .version("HTTP/1.1")
                .header("Host", host.getHostName())
                .getRequest()
        );
    }

    /** Helper method for making POST requests, invokes request() with a path and body
     *
     * @param host Valid InetAddress which corresponds to the desired host
     * @param path Path to request to
     * @param body Payload to send to the sever
     * @return the HttpResponse object which was returned by the host
     * @throws IOException when there is an error in the Socket connection
     */
    public static HttpResponse quickPost(InetSocketAddress host, String path, String body) throws IOException {
        return request(host, new RequestBuilder()
                .method(HttpMethod.POST)
                .path(URI.create(path))
                .version("HTTP/1.1")
                .header("Host", host.getHostName())
                .body(body)
                .getRequest()
        );
    }
}
