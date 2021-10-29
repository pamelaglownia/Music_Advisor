package pl.glownia.pamela.clientserverhttp;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;

class ClientHTTP {

    static void sendRequestToServer() {
        try {
            HttpClient client = HttpClient.newBuilder().build();
            String stringToParam = "username";
            String url = "http://localhost:8080/?name=" + stringToParam;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}