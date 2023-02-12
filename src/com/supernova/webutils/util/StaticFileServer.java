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

package com.supernova.webutils.util;

import com.supernova.webutils.Application;
import com.supernova.webutils.http.HttpRequest;
import com.supernova.webutils.http.HttpResponse;
import com.supernova.webutils.http.HttpStatus;
import com.supernova.webutils.http.ResponseBuilder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The `StaticFileServer` class implements the {@link Application} interface, providing a basic HTTP server
 * that serves static files located in a specified directory.
 *
 * <p>The base directory, where the files are stored, can be set using the {@link #setBaseDir(String)} method.
 * When a client sends an HTTP request, the server retrieves the file located at the path specified by the request's
 * URI and returns it as the body of the response, along with the appropriate headers. If the file is not found or is
 * a directory, the server returns a 404 Not Found response.
 *
 * <p>The response's body is populated with the contents of the file, and the Content-Type header is set based on
 * the file's extension.
 *
 * @author OpenAI
 * @since 1.1
 */
public class StaticFileServer implements Application {
    private static String baseDir;

    /**
     * Implements the {@link Application#process(HttpRequest)} method, processing the incoming HTTP request and
     * returning an HTTP response.
     *
     * @param request the incoming HTTP request
     * @return the HTTP response
     * @throws Exception if an error occurs while processing the request
     */
    @Override
    public HttpResponse process(HttpRequest request) throws Exception {
        ResponseBuilder builder = new ResponseBuilder();
        Path filePath = Paths.get(baseDir, request.getUri().getRawPath());
        File file = filePath.toFile();

        if((!file.exists() || file.isDirectory())) return MessageHelper.resError(HttpStatus.NOT_FOUND);

        builder.status(HttpStatus.OK);
        builder.body(new String(Files.readAllBytes(filePath)));
        builder.header("Content-Type", MimeType.valueOf(file.getName().split("\\.")[1]).getMime());

        return builder.getResponse();
    }

    /**
     * Sets the base directory for the server, where the static files are stored.
     *
     * @param baseDir the path to the base directory
     */
    public static void setBaseDir(String baseDir) {
        StaticFileServer.baseDir = baseDir;
    }
}

