package pl.glownia.pamela.clientserverhttp;

public class Authorization {
    User user;
    boolean isAuthorized;

    public Authorization() {
        user = new User("df36e5b6fbff48f6946be56ece7a9ef1", "a42ffbc131b3472d8cba29a62a46d7c4");
        isAuthorized = false;
    }

    public boolean isClientAuthorized() {
        return isAuthorized;
    }

    public void accessApp() {
        ClientServerHTTP.startHTTPServer();
        System.out.println("Use this link to request the access code:");
        StringBuilder authorizationLink = new StringBuilder();
        authorizationLink.append("https://accounts.spotify.com/authorize?client_id=");
        authorizationLink.append(user.getName());
        authorizationLink.append("&redirect_uri=http://localhost:8080/user&response_type=code");
        System.out.println(authorizationLink);
        ClientServerHTTP.getAccessToken(user);
        isAuthorized = true;
    }
}