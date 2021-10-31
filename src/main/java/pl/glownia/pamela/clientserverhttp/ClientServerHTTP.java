package pl.glownia.pamela.clientserverhttp;

public class ClientServerHTTP {
    private static final User sampleUser = new User("df36e5b6fbff48f6946be56ece7a9ef1", "a42ffbc131b3472d8cba29a62a78d7c4");

    public static void startHTTPServer() {
        ServerHTTP.listen();
        ClientHTTP.sendRequestForConnection();
        ServerHTTP.stopServer(3);
    }

    public static void sendRequestToGetToken() {
        ServerHTTP.createServer();
        String code = ServerHTTP.createRequestContext();
        ServerHTTP.start();
        ClientHTTP.sendRequestToGetToken(sampleUser, code);
    }

    public static String getUserId() {
        return sampleUser.getName();
    }
}