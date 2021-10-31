package pl.glownia.pamela.clientserverhttp;

import java.net.*;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;

class ClientHTTP {

    private static final HttpClient client = HttpClient.newBuilder().build();

    static void sendRequestForConnection() {
        try {
            String userName = "user";
            String url = "http://localhost:8080/?user=" + userName;
            HttpRequest requestToConnect = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(requestToConnect, HttpResponse.BodyHandlers.ofString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    static void sendRequestToGetToken(User user, String code) {
        try {
            String url = "https://accounts.spotify.com/api/token";
            HttpRequest requestToGetAccessToken = HttpRequest.newBuilder()
                    .setHeader("Content-Type", "application/x-www-form-urlencoded")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString("client_id=" + user.getName() +
                            "&client_secret=" + user.getPassword() +
                            "&grant_type=authorization_code" +
                            "&code=" + code +
                            "&redirect_uri=" + url))
                    .build();
            HttpResponse<String> response = client.send(requestToGetAccessToken, HttpResponse.BodyHandlers.ofString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}