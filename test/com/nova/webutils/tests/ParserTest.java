package test.com.nova.webutils.tests;
import org.junit.jupiter.api.Test;

import com.nova.webutils.http.HttpMethod;
import com.nova.webutils.http.HttpParser;
import com.nova.webutils.http.HttpRequest;
import com.nova.webutils.http.RequestBuilder;

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
        String correctStr = HttpParser.serializeRequest(correct);
        assertEquals(reqStr, correctStr);
    }
}
