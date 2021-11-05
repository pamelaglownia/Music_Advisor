package pl.glownia.pamela.clientserverhttp;

import org.json.JSONArray;
import org.json.JSONObject;

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
        System.out.println("Use this link to request the access code:");
        StringBuilder authorizationLink = new StringBuilder();
        authorizationLink.append("https://accounts.spotify.com/authorize?client_id=");
        authorizationLink.append(user.getName());
        authorizationLink.append("&redirect_uri=http://localhost:8080/user&response_type=code");
        System.out.println(authorizationLink);
        String accessToken = ClientServerHTTP.getAccessToken(user);
        isAuthorized = true;
        return accessToken;
    }

    public void getCategories(String accessToken) {
        String urlCategories = "https://api.spotify.com/v1/browse/categories";
        String responseFromServer = ClientServerHTTP.getAccessToChosenPartOfApp(accessToken, urlCategories);
        JSONObject jsonObject = new JSONObject(responseFromServer);
        JSONObject categories = jsonObject.getJSONObject("categories");
        JSONArray items = categories.getJSONArray("items");
        for (Object object : items) {
            JSONObject element = (JSONObject) object;
            String nameOfCategory = element.getString("name");
            System.out.println(nameOfCategory);
        }
    }

    public void getNewReleases(String accessToken) {
        String urlCategories = "https://api.spotify.com/v1/browse/new-releases";
        String responseFromServer = ClientServerHTTP.getAccessToChosenPartOfApp(accessToken, urlCategories);
        System.out.println(responseFromServer);
        JSONObject jsonObject = new JSONObject(responseFromServer);
        JSONObject albums = jsonObject.getJSONObject("albums");
        JSONArray items = albums.getJSONArray("items");
        for (Object object : items) {
            JSONObject element = (JSONObject) object;
            String albumName = element.getString("name");
            System.out.println(albumName);
            JSONArray artists = element.getJSONArray("artists");
            for (Object artist : artists) {
                JSONObject currentArtist = (JSONObject) artist;
                String artistName = currentArtist.getString("name");
                System.out.print(artistName + " ");
            }
            System.out.println();
            String url = element.getString("href");
            System.out.println(url);
        }
    }
}