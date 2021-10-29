package pl.glownia.pamela.clientserverhttp;

public class ClientServerHTTP {
    public static void startHTTPServer() {
        ServerHTTP.listen();
        ClientHTTP.sendRequestToServer();
        ServerHTTP.stopServer(3);
    }
}