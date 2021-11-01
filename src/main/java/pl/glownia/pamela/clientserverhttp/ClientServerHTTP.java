package pl.glownia.pamela.clientserverhttp;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ClientServerHTTP {
    private static final User sampleUser = new User("df36e5b6fbff48f6946be56ece7a9ef1", "a42ffbc131b3472d8cba29a62a78d7c4");
    private static HttpServer server;
    private static final HttpClient client = HttpClient.newBuilder().build();
    private static String authorizationCode = "";

    public static String getUserId() {
        return sampleUser.getName();
    }

    static void createServer() {
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startHTTPServer() {
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

    static void sendRequestToGetToken() {
        try {
            String encodedData = Base64.getEncoder().encodeToString((sampleUser.getName() + ":" + sampleUser.getPassword()).getBytes(StandardCharsets.UTF_8));
            String url = "https://accounts.spotify.com/api/token";
            String redirectUri = "http://localhost:8080/user";
            System.out.println("Code: " + authorizationCode);
            HttpRequest requestToGetAccessToken = HttpRequest.newBuilder()
                    .setHeader("Authorization", "Basic " + encodedData)
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code" +
                            "&client_id=" + sampleUser.getName() +
                            "&client_secret=" + sampleUser.getPassword() +
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

    public static void getAccessToken() {
        getAuthorizationCode();
        sendRequestToGetToken();
    }
}