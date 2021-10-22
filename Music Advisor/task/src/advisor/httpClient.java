package advisor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class httpClient {
   public final String id = "085b812857a44fc1aa6b496a251cb3cf";
    public final String secret_id = "b2b0a38c68724b5caacb5b13cfdf2f61";
    static String complete_response;

    private HttpClient client;

    private HttpClient buildClient() {
        this.client = HttpClient.newBuilder()
                .build();
        return client;
    }

    public HttpRequest buildRequest() {
        String uri;
        uri = Objects.requireNonNullElse(Main.access, "https://accounts.spotify.com");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/api/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code&redirect_uri=http://localhost:8080&" +
                        "client_id=" + id + "&client_secret=" + secret_id + "&code=" + httpHandler.code))
                .build();
        return request;
    }

    public void sendRequest() {
        try {
            HttpResponse<String> response = buildClient().send(buildRequest(), HttpResponse.BodyHandlers.ofString());
            complete_response = response.body();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

}
