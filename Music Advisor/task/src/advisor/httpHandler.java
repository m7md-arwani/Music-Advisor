package advisor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;

public class httpHandler implements HttpHandler {
    // "code" will be accessed by other classes.
    public static String code;


    private static final String EQUAL_DELIMITER = "=";


    @Override
    public void handle(HttpExchange t) throws IOException {
        URI uri = t.getRequestURI();
        String response = createResponseFromQueryParams(uri);
        t.sendResponseHeaders(200, response.length());
        t.getResponseBody().write(response.getBytes());
        t.getResponseBody().close();
    }

    private String createResponseFromQueryParams(URI uri) {

        String query = uri.getQuery();
        if (query.contains("code")) {
            String[] text = query.split(EQUAL_DELIMITER);
            code = text[1];
            return "Got the code. Return back to your program.";


        } else
            code = "does not exist";
            return "Authorization code not found. Try again.";
        }

    }

