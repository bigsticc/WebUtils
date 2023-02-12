# WebUtils

WebUtils is a simple, no-dependency* Java toolkit that allows you to run your web apps with very little complication,
and make web requests with ease.

<sub>
Disclaimer: This is my first programming project that has been published, and I am only a hobbyist developer. 
I apologize in advance if this repository contains absolutely abhorrent code.
</sub>
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

<img src="https://img.shields.io/badge/license-GPL--3.0-red"/>
