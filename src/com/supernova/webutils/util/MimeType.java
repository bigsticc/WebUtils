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

/**
 * This enum defines a list of MIME types commonly used in web applications.
 *
 * <p>Each value in the enum represents a MIME type, with a corresponding string representation
 * of the MIME type stored in the `mime` field. The `getMime` method can be used to retrieve the
 * string representation of the MIME type, while the `getByMime` method can be used to retrieve the
 * `MimeType` enum value corresponding to a given string representation of a MIME type.
 *
 * <p>Here's an example usage of the `MimeType` enum:
 * <pre>
 * {@code
 * String mime = "image/jpeg";
 * MimeType mt = MimeType.getByMime(mime);
 * System.out.println(mt.getMime()); // Outputs "image/jpeg"
 * }
 * </pre>
 * @author Supernova
 * @since 1.0
 */
public enum MimeType {

    aac("audio/aac"),
    abw("application/x-abiword"),
	arc("application/x-freearc"),
	avif("image/avif"),
	avi("video/x-msvideo"),
	azw("application/vnd.amazon.ebook"),
	bin("application/octet-stream"),
	bmp("image/bmp"),
	bz("application/x-bzip"),
	bz2("application/x-bzip2"),
	cda("application/x-cdf"),
	csh("application/x-csh"),
	css("text/css"),
	csv("text/csv"),
	doc("application/msword"),
	docx("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
	eot("application/vnd.ms-fontobject"),
	epub("application/epub+zip"),
	gz("application/gzip"),
	gif("image/gif"),
	htm("text/html"),
	html("text/html"),
	ico("image/vnd.microsoft.icon"),
	ics("text/calendar"),
	jar("application/java-archive"),
	jpeg("image/jpeg"),
	jpg("image/jpeg"),
	js("text/javascript"),
	json("application/json"),
	jsonld("application/ld+json"),
	mid("audio/midi"),
	midi("audio/midi"),
	mjs("text/javascript"),
	mp3("audio/mpeg"),
	mp4("video/mp4"),
	mpeg("video/mpeg"),
	mpkg("application/vnd.apple.installer+xml"),
	odp("application/vnd.oasis.opendocument.presentation"),
	ods("application/vnd.oasis.opendocument.spreadsheet"),
	odt("application/vnd.oasis.opendocument.text"),
	oga("audio/ogg"),
	ogv("video/ogg"),
	ogx("application/ogg"),
	opus("audio/opus"),
	otf("font/otf"),
	png("image/png"),
	pdf("application/pdf"),
	php("application/x-httpd-php"),
	ppt("application/vnd.ms-powerpoint"),
	pptx("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
	rar("application/vnd.rar"),
	rtf("application/rtf"),
	sh("application/x-sh"),
	svg("image/svg+xml"),
	tar("application/x-tar"),		
    tiff("image/tiff"),
	ts("video/mp2t"),
	ttf("font/ttf"),
	txt("text/plain"),
	vsd("application/vnd.visio"),
	wav("audio/wav"),
	weba("audio/webm"),
	webm("video/webm"),
	webp("image/webp"),
	woff("font/woff"),
	woff2("font/woff2"),
	xhtml("application/xhtml+xml"),
	xls("application/vnd.ms-excel"),
	xlsx("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
	xml("application/xml"),
	xul("application/vnd.mozilla.xul+xml"),
	zip("application/zip"),
	_3gp("video/3gpp"),
	_3g2("video/3gpp2"),
	_7z("application/x-7z-compressed");

    final String mime;

    MimeType(String mime) {
      this.mime = mime;
    }

    public String getMime() {
      return mime;
    }
    public static String getByMime(String mime) {
      for(MimeType m : MimeType.values()) {
        if(m.getMime().equals(mime)) return mime;
      }
      return null;
    }
}