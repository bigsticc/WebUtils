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
