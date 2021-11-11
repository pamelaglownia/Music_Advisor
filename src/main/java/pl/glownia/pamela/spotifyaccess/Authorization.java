package pl.glownia.pamela.spotifyaccess;

class Authorization {
    User user;
    boolean isAuthorized;

     Authorization() {
        user = new User("df36e5b6fbff48f6946be56ece7a9ef1", "a42ffbc131b3472d8cba29a62a46d7c4");
        isAuthorized = false;
    }

    boolean isClientAuthorized() {
        return isAuthorized;
    }

    String accessApp() {
        ClientServerHTTP.startHTTPServer();
        System.out.println(SpotifyUrl.getAuthorizationLink(user.getName()));
        String accessToken = ClientServerHTTP.getAccessToken(user);
        if (accessToken == null) {
            isAuthorized = false;
            System.out.println("Authorization is not possible.");
        }
        isAuthorized = true;
        return accessToken;
    }
}