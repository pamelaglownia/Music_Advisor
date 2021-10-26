package pl.glownia.pamela;

class Authorization {
    String clientId;
    boolean isAuthorized;

    Authorization(String clientId) {
        this.clientId = clientId;
        isAuthorized = false;
    }

    boolean isClientAuthorized() {
        return isAuthorized;
    }

    void accessApp() {
        StringBuilder authorizationLink = new StringBuilder();
        authorizationLink.append("https://accounts.spotify.com/authorize?client_id=");
        authorizationLink.append(clientId);
        authorizationLink.append("&redirect_uri=https://www.example.com&response_type=code");
        isAuthorized = true;
        System.out.println(authorizationLink);
    }
}
