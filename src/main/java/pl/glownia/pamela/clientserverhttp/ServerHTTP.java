package pl.glownia.pamela.clientserverhttp;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

class ServerHTTP {
    private static HttpServer server;

    static void createServer() {
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void createRequestContext() {
        server.createContext("/",
                //HttpHandler
                exchange -> {
                    String query = exchange.getRequestURI().getQuery();
                    if (query == null) {
                        query = "Query is null";
                    }
                    exchange.sendResponseHeaders(200, query.length());
                    exchange.getResponseBody().write(query.getBytes());
                    exchange.getResponseBody().close();
                });
    }

    static void listen() {
        createServer();
        createRequestContext();
        server.start();
    }

    static void stopServer(int delay) {
        server.stop(delay);
    }
}