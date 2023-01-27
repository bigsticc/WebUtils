package com.nova.webutils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HttpParser {

    public static HttpRequest parseRequest(InputStream inputStream) throws IOException {
        // read the input stream and parse the request
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String requestLine = reader.readLine();
        String[] requestLineComponents = requestLine.split(" ");
        HttpMethod method = HttpMethod.valueOf(requestLineComponents[0]);
        URI uri = URI.create(requestLineComponents[1]);
        String version = requestLineComponents[2];
        Map<String, String> headers = parseHeaders(reader);
        String body = parseBody(reader, headers);
        
        // create a new HttpRequest object and set its fields
        HttpRequest request = new HttpRequest();
        request.setMethod(method);
        request.setUri(uri);
        request.setVersion(version);
        request.setHeaders(headers);
        request.setBody(body);
        
        return request;
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
