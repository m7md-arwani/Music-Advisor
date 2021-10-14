package advisor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class httpClient {
    final String  id = "085b812857a44fc1aa6b496a251cb3cf";
    final String secret_id = "b03c466c6ecc41c0b5438b87d0084fcb";
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
                .uri(URI.create(uri+"/api/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code&redirect_uri=http://localhost:8080&" +
                        "client_id=" + id + "&client_secret=" + secret_id +"&code=" + httpHandler.code))
                .build();
        return request;
    }

    public void sendPostRequest() {
       try {
           HttpResponse<String> response = buildClient().send(buildRequest(),HttpResponse.BodyHandlers.ofString());
           complete_response = response.body();
       } catch (Exception ex) {ex.printStackTrace();}



    }

}
