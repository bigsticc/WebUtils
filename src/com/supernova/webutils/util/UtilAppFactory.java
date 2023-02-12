package com.supernova.webutils.util;

import com.supernova.webutils.Application;
import com.supernova.webutils.http.HttpStatus;
import com.supernova.webutils.http.ResponseBuilder;

import java.nio.file.Files;
import java.nio.file.Path;

/** Basic, generic applications which can be quickly configured and plugged into a server.
 *
 * @author Supernova
 * @since 1.0
 */
public class UtilAppFactory {
    public static Application staticServer(String root) {
        return req -> {
            String path = root + req.getUri().getQuery();
            Path f = Path.of(path);
            return new ResponseBuilder()
                    .version("HTTP/1.1")
                    .status(HttpStatus.OK)
                    .header("Content-Type", MimeType.valueOf(f.getFileName().toString().split("\\.")[1]).getMime())
                    .body(Files.readString(f))
                    .getResponse();
        };
    }
}
