package pl.glownia.pamela.spotifyaccess;

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

    public String accessApp() {
        ClientServerHTTP.startHTTPServer();
        System.out.println(SpotifyUrl.getAuthorizationLink(user.getName()));
        String accessToken = ClientServerHTTP.getAccessToken(user);
        isAuthorized = true;
        return accessToken;
    }
}