# WebUtils

WebUtils is a simple, no-dependency* Java toolkit that allows you to run your web apps with very little complication,
and make web requests with ease.

<br><br>
<sub>*no runtime dependencies, JUnit 5 Jupiter is required at test-time</sub>


## Building from source
- Install JDK19 and Apache Ant
- Clone https://github.com/Supernova9987/WebUtils.git
- In the directory you have created, run `ant build`
- The new .jar should be in `build/dist/`, and javadoc in `build/docs/`
- Run `ant clean` to delete building and testing artifacts

## How to use
To use WebUtils, include the JAR Archive in your classpath, then create a class implementing 
`com.supernova.webutils.Application`. Then create an `AppServer` instance and register the app.
Finally, start the server on a port, and it will be ready to listen.


## Example
Here is an `ExampleApp` class, which provides a brief demonstration of WebUtils' capabilities.

```Java
import com.supernova.webutils.Application;
import com.supernova.webutils.AppServer;
import http.com.supernova.webutils.HttpRequest;
import http.com.supernova.webutils.HttpResponse;
import util.com.supernova.webutils.MessageHelper;

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
import com.supernova.webutils.HttpClient;
import http.com.supernova.webutils.HttpParser;
import util.com.supernova.webutils.MessageHelper;

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

## Contributing

We encourage you to make any positive contribution that you can, but please observe the guidelines layed out in [CONTRIBUTING.MD](CONTRIBUTING.md)

<img src="https://img.shields.io/badge/license-GPL--3.0-red"/>
