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
    public void testParse() throws IOException {
        String req = """
        POST /path/to/resource HTTP/1.1
        Host: www.example.com
        Content-Type: application/json
        Content-Length: 42
        {
            \"name\": \"John Smith\",
            \"age\": 30
        }
        """;

        HttpRequest request = HttpParser.parseRequest(new ByteArrayInputStream(req.getBytes()));

        HttpRequest correct = new RequestBuilder()
            .method(HttpMethod.POST)
            .path(URI.create("/path/to/resource"))
            .header("Host", "www.example.com")
            .header("Content-Type", "application/json")
            .header("Content-Length", "42")
            .body("""
            {
                \"name\": \"John Smith\",
                \"age\": 30
            }        
            """)
            .getRequest();
        
        assertEquals(correct, request);
    }
}
