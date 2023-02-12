package com.supernova.webutils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/** Converts requests/responses to and from strings
 *  @author Supernova
 *  @since 1.0
 */
public class HttpParser {
    public static String serializeRequest(HttpRequest req) {
        StringBuilder sb = new StringBuilder();

        // Building request line
        sb
        .append(req.getMethod().name())
        .append(" ")
        .append(req.getUri().toString())
        .append(" ")
        .append(req.getVersion())
        .append("\r\n");

        // Putting headers in request
        for(Map.Entry<String,String> header : req.getHeaders().entrySet()) {
            sb.append(header.getKey()).append(":").append(header.getValue()).append("\r\n");
        }

        // Adding empty line, and appending body
        sb.append("\r\n").append(req.getBody());

        return sb.toString();
    }
    public static String serializeResponse(HttpResponse res) {
        StringBuilder sb = new StringBuilder();
        sb
        .append(res.version)
        .append(" ")
        .append(res.status.getStatus())
        .append(" ")
        .append(res.status.getReasonPhrase())
        .append("\r\n");

        for(Map.Entry<String, String> header : res.getHeaders().entrySet()) {
            sb.append(header.getKey()).append(":").append(header.getValue()).append("\r\n");
        }

        sb.append("\r\n").append(res.getBody());
        
        return sb.toString();
    }

    public static HttpRequest parseRequest(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String requestLine = reader.readLine();
        String[] requestLineComponents = requestLine.split(" ");

        HttpMethod method = HttpMethod.valueOf(requestLineComponents[0]);
        URI uri = URI.create(requestLineComponents[1]);
        String version = requestLineComponents[2];

        Map<String, String> headers = parseHeaders(reader);
        String body = parseBody(reader, headers);
        
        reader.close();

        return new HttpRequest(method, uri, version, headers, body);
    }
    public static HttpResponse parseResponse(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String requestLine = reader.readLine();
        String[] requestLineComponents = requestLine.split(" ");

        String version = requestLineComponents[0];
        HttpStatus status = HttpStatus.getStatus(Integer.parseInt(requestLineComponents[1]));

        Map<String, String> headers = parseHeaders(reader);
        String body = parseBody(reader, headers);
        return new HttpResponse(version, status, headers, body);
    }


    private static Map<String, String> parseHeaders(BufferedReader reader) throws IOException {
        Map<String, String> headers = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null && !line.isEmpty()) {
            int colonIndex = line.indexOf(':');
            if (colonIndex > 0) {
                String key = line.substring(0, colonIndex).trim();
                String value = line.substring(colonIndex + 1).trim();
                headers.put(key, value);
            }
        }
        return headers;
    }
    private static String parseBody(BufferedReader reader, Map<String, String> headers) throws IOException {
        StringBuilder body = new StringBuilder();
        if (headers.containsKey("Content-Length")) {
            int contentLength = Integer.parseInt(headers.get("Content-Length"));
            for (int i = 0; i < contentLength; i++) {
                body.append((char) reader.read());
            }
        }
        return body.toString();
    }
}
