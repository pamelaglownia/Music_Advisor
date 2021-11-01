package pl.glownia.pamela;

import pl.glownia.pamela.clientserverhttp.ClientServerHTTP;

class Authorization {
    String userId;
    boolean isAuthorized;

    Authorization() {
        this.userId = getUserId();
        isAuthorized = false;
    }

    String getUserId() {
        return ClientServerHTTP.getUserId();
    }

    boolean isClientAuthorized() {
        return isAuthorized;
    }

    void accessApp() {
        ClientServerHTTP.startHTTPServer();
        System.out.println("Use this link to request the access code:");
        StringBuilder authorizationLink = new StringBuilder();
        authorizationLink.append("https://accounts.spotify.com/authorize?client_id=");
        authorizationLink.append(userId);
        authorizationLink.append("&redirect_uri=http://localhost:8080/user&response_type=code");
        System.out.println(authorizationLink);
        ClientServerHTTP.getAccessToken();
        isAuthorized = true;
    }
}