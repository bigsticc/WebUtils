package com.nova.webutils.util;

import com.nova.webutils.Application;
import com.nova.webutils.http.HttpStatus;
import com.nova.webutils.http.ResponseBuilder;

import java.nio.file.Files;
import java.nio.file.Path;

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
