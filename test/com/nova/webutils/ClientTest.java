package test.com.nova.webutils;

import com.nova.webutils.client.HttpClient;
import com.nova.webutils.http.HttpMethod;
import com.nova.webutils.http.HttpParser;
import com.nova.webutils.http.HttpResponse;
import com.nova.webutils.http.RequestBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    public void exampleTest() throws IOException {
        HttpClient myClient = new HttpClient();
        HttpResponse res = myClient.request(new InetSocketAddress("example.com", 80), new RequestBuilder()
            .method(HttpMethod.GET)
            .path(URI.create("/"))
            .version("HTTP/1.1")

            .header("Host", "example.com")
            .getRequest());
        assertNotNull(res);
        System.out.println(HttpParser.serializeResponse(res));
    }
}
