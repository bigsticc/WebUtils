package test.com.nova.webutils;
import org.junit.jupiter.api.Test;

import com.nova.webutils.http.HttpMethod;
import com.nova.webutils.http.HttpParser;
import com.nova.webutils.http.HttpRequest;
import com.nova.webutils.http.HttpResponse;
import com.nova.webutils.http.HttpStatus;
import com.nova.webutils.http.RequestBuilder;
import com.nova.webutils.http.ResponseBuilder;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;

public class ParserTest {
    @Test
    public void testReqParse() throws IOException {
        String req = """
        POST /path/to/resource HTTP/1.1
        Host: www.example.com
        Content-Type: application/json
        Content-Length: 32

        {\"name\": \"John Smith\",\"age\": 30}
        """;

        HttpRequest request = HttpParser.parseRequest(new ByteArrayInputStream(req.getBytes()));

        HttpRequest correct = new RequestBuilder()
            .method(HttpMethod.POST)
            .path(URI.create("/path/to/resource"))
            .version("HTTP/1.1")
            .header("Host", "www.example.com")
            .header("Content-Type", "application/json")
            .header("Content-Length", "32")
            .body("{\"name\": \"John Smith\",\"age\": 30}")
            .getRequest();
        
        String reqStr = HttpParser.serializeRequest(request);
        String testStr = HttpParser.serializeRequest(correct);
        assertEquals(reqStr, testStr);
    }

    @Test
    public void testResParse() throws IOException {
        String res = """
        HTTP/1.1 200 OK
        Content-Type: application/json
        Content-Length: 32

        {\"name\": \"John Smith\",\"age\": 30}       
        """;

        HttpResponse response = HttpParser.parseResponse(new ByteArrayInputStream(res.getBytes()));

        HttpResponse correct = new ResponseBuilder()
        .version("HTTP/1.1")
        .status(HttpStatus.OK)
        .header("Content-Type", "application/json")
        .header("Content-Length", "32")
        .body("{\"name\": \"John Smith\",\"age\": 30}")
        .getResponse();

        String resStr = HttpParser.serializeResponse(response);
        String testStr = HttpParser.serializeResponse(correct);
        assertEquals(resStr, testStr);
    }
}
