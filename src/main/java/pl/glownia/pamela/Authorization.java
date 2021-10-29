package pl.glownia.pamela;

import pl.glownia.pamela.clientserverhttp.ClientServerHTTP;

class Authorization {
    String clientId;
    boolean isAuthorized;

    Authorization(String clientId) {
        this.clientId = clientId;
        isAuthorized = false;
    }

    void startHTTPServer() {
        ClientServerHTTP.startHTTPServer();
    }

    boolean isClientAuthorized() {
        return isAuthorized;
    }

    void accessApp() {
        System.out.println("Use this link to request the access code:");
        StringBuilder authorizationLink = new StringBuilder();
        authorizationLink.append("https://accounts.spotify.com/authorize?client_id=");
        authorizationLink.append(clientId);
        authorizationLink.append("&redirect_uri=http://localhost:8080/username&response_type=code");
        isAuthorized = true;
        System.out.println(authorizationLink);
    }
}