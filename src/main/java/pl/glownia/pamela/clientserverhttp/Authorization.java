package pl.glownia.pamela.clientserverhttp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        JSONArray categoryItems = categories.getJSONArray("items");
        for (Object category : categoryItems) {
            JSONObject currentCategory = (JSONObject) category;
            String nameOfCategory = currentCategory.getString("name");
            System.out.println(nameOfCategory);
        }
    }

    public void getNewReleases(String accessToken) {
        String urlNewReleases = "https://api.spotify.com/v1/browse/new-releases";
        String responseFromServer = ClientServerHTTP.getAccessToChosenPartOfApp(accessToken, urlNewReleases);
        JSONObject jsonObjectFromResponse = new JSONObject(responseFromServer);
        JSONObject albums = jsonObjectFromResponse.getJSONObject("albums");
        JSONArray albumsItems = albums.getJSONArray("items");
        for (Object item : albumsItems) {
            JSONObject albumObject = (JSONObject) item;
            String albumName = albumObject.getString("name");
            System.out.println(albumName.toUpperCase());
            JSONArray artists = albumObject.getJSONArray("artists");
            List<String> artistsToPrint = new ArrayList<>();
            for (Object artist : artists) {
                JSONObject currentArtist = (JSONObject) artist;
                String artistName = currentArtist.getString("name");
                artistsToPrint.add(artistName);
            }
            System.out.println(artistsToPrint);
            JSONObject externalUrl = albumObject.getJSONObject("external_urls");
            String albumUrl = externalUrl.getString("spotify");
            System.out.println(albumUrl);
            System.out.println();
        }
    }

    public void getFeaturedPlaylists(String accessToken) {
        String featuredPlaylists = "https://api.spotify.com/v1/browse/featured-playlists";
        String responseFromServer = ClientServerHTTP.getAccessToChosenPartOfApp(accessToken, featuredPlaylists);
        JSONObject jsonObjectFromResponse = new JSONObject(responseFromServer);
        JSONObject playlists = jsonObjectFromResponse.getJSONObject("playlists");
        JSONArray playlistsItems = playlists.getJSONArray("items");
        for (Object playlist : playlistsItems) {
            JSONObject currentPlaylist = (JSONObject) playlist;
            String playlistName = currentPlaylist.getString("name");
            System.out.println(playlistName);
            JSONObject externalUrl = currentPlaylist.getJSONObject("external_urls");
            String url = externalUrl.getString("spotify");
            System.out.println(url);
            System.out.println();
        }
    }
}