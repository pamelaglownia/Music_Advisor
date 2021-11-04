package pl.glownia.pamela.clientserverhttp;

import com.sun.net.httpserver.HttpServer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

    static String sendRequestToGetToken(User user) {
        String accessToken = "";
        try {
            String url = "https://accounts.spotify.com/api/token";
            String redirectUri = "http://localhost:8080/user";
            HttpRequest requestToGetAccessToken = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code" +
                            "&client_id=" + user.getName() +
                            "&client_secret=" + user.getPassword() +
                            "&code=" + authorizationCode +
                            "&redirect_uri=" + redirectUri))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            HttpResponse<String> response = client.send(requestToGetAccessToken, HttpResponse.BodyHandlers.ofString());
            System.out.println("Success!");
            JSONObject jsonObject = new JSONObject(response.body());
            accessToken = jsonObject.getString("access_token");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return accessToken;
    }

    static String getAccessToken(User user) {
        getAuthorizationCode();
        return sendRequestToGetToken(user);
    }

    static void getAccessToCategories(String accessToken, String url) {
        try {
            HttpRequest requestToGetNewReleases = HttpRequest.newBuilder()
                    .header("Authorization", "Bearer " + accessToken)
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(requestToGetNewReleases, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            JSONObject categories = jsonObject.getJSONObject("categories");
            JSONArray items = categories.getJSONArray("items");
            for (Object object : items) {
                JSONObject element = (JSONObject) object;
                String nameOfCategory = element.getString("name");
                System.out.println(nameOfCategory);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}