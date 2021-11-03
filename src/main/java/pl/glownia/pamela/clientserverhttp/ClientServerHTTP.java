package pl.glownia.pamela.clientserverhttp;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

class ClientServerHTTP {
    private static HttpServer server;
    private static final HttpClient client = HttpClient.newBuilder().build();
    private static String authorizationCode = "";

    static void createServer() {
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void startHTTPServer() {
        createServer();
        server.start();
    }

    static void getAuthorizationCode() {
        server.createContext("/", exchange -> {
            String text;
            String query = exchange.getRequestURI().getQuery();
            if (query != null && query.contains("code=")) {
                text = "Got the code. Return back to your program.";
                authorizationCode = query.substring(5);
            } else {
                text = "Authorization code not found. Try again.";
                System.out.println(text);
            }
            exchange.sendResponseHeaders(200, text.length());
            exchange.getResponseBody().write(text.getBytes());
            exchange.getResponseBody().close();
        });
        while (authorizationCode.equals("")) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        server.stop(5);
    }

    static void sendRequestToGetToken(User user) {
        try {
            String encodedData = Base64.getEncoder().encodeToString((user.getName() + ":" + user.getPassword()).getBytes(StandardCharsets.UTF_8));
            String url = "https://accounts.spotify.com/api/token";
            String redirectUri = "http://localhost:8080/user";
            HttpRequest requestToGetAccessToken = HttpRequest.newBuilder()
                    .setHeader("Authorization", "Basic " + encodedData)
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code" +
                            "&client_id=" + user.getName() +
                            "&client_secret=" + user.getPassword() +
                            "&code=" + authorizationCode +
                            "&redirect_uri=" + redirectUri))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            HttpResponse<String> response = client.send(requestToGetAccessToken, HttpResponse.BodyHandlers.ofString());
            System.out.println("Access_token: " + response.body());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void getAccessToken(User user) {
        getAuthorizationCode();
        sendRequestToGetToken(user);
    }
}