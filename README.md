# WebUtils

WebUtils is a simple Java toolkit that allows you to run your web apps with very little complication,
and make web requests with ease.

## How to use
To use WebUtils, include the JAR Archive in your classpath, then create a class implementing 
`com.nova.webutils.Application`. Then create an `AppServer` instance and register the app.
Finally, start the server on a port, and it will be ready to listen.

## Example
Here is an `ExampleApp` class, which provides a brief demonstration of WebUtils' capabilities.

```Java
import com.nova.webutils.Application;
import com.nova.webutils.AppServer;
import com.nova.webutils.http.HttpRequest;
import com.nova.webutils.http.HttpResponse;
import com.nova.webutils.util.MessageHelper;

public class ExampleApp implements Application {
    @Override
    public HttpResponse process(HttpRequest req) {
        return MessageHelper.quickHtml(
                "<html><h1>Hello World!</h1></html>"
        );
    }

    public static void main(String[] args) {
        AppServer myServer = new AppServer(8080);
        myServer.registerApp("/", ExampleApp.class);
        myServer.start();
    }
}
```

### Client Example
Here is a second example, demonstrating WebUtils' capabilities as a client

```Java
import com.nova.webutils.HttpClient;
import com.nova.webutils.http.HttpParser;
import com.nova.webutils.util.MessageHelper;

import java.net.InetSocketAddress;

public class Foo {
    public static void main(String[] args) {
        HttpResponse res = HttpClient.request(
                new InetSocketAddress("google.com", 80),
                MessageHelper.getPage("google.com", "/")
        );
        System.out.println(HttpParser.serializeResponse(res));
    }
}
```