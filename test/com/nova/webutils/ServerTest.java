package test.com.nova.webutils;
import java.io.IOException;

import com.nova.webutils.http.HttpStatus;
import com.nova.webutils.http.ResponseBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.nova.webutils.http.HttpRequest;
import com.nova.webutils.http.HttpResponse;
import com.nova.webutils.server.AppServer;
import com.nova.webutils.server.Application;

public class ServerTest {
    @Test
    public void serverReqTest() throws IOException, InterruptedException {
        AppServer server = new AppServer(8080);

        server.registerApp("/", TestApp.class);
        server.start();

        Thread.sleep(20000);

        assertTrue(true);
    }
    private static class TestApp implements Application {
        @Override
        public HttpResponse process(HttpRequest req) {
            return new ResponseBuilder()
                    .version("HTTP/1.1")
                    .status(HttpStatus.OK)
                    .header("Content-Type", "text/html")
                    .header("Content-Length", "34")
                    .body("<html><h1>Hello World!</h1></html>")
            .getResponse();
        }

    }
}
