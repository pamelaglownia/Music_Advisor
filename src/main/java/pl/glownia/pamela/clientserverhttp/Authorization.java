package pl.glownia.pamela.clientserverhttp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Authorization {
    User user;
    boolean isAuthorized;
    private final String urlCategories = "https://api.spotify.com/v1/browse/categories";


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

    public List<String> getCategories(String accessToken) {
        String responseFromServer = ClientServerHTTP.getAccessToChosenPartOfApp(accessToken, urlCategories);
        JSONObject jsonObject = new JSONObject(responseFromServer);
        JSONObject categories = jsonObject.getJSONObject("categories");
        JSONArray categoryItems = categories.getJSONArray("items");
        List<String> listOfCategories = new ArrayList<>();
        for (Object category : categoryItems) {
            JSONObject currentCategory = (JSONObject) category;
            String nameOfCategory = currentCategory.getString("name");
            listOfCategories.add(nameOfCategory);
            System.out.println(nameOfCategory);
        }
        return listOfCategories;
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

    JSONObject getJsonObjectFromServerResponse(String url, String accessToken) {
        return new JSONObject(ClientServerHTTP.getAccessToChosenPartOfApp(accessToken, url));
    }

    void getPlaylist(JSONObject jsonObjectFromResponse) {
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

    public void getFeaturedPlaylists(String accessToken) {
        String featuredPlaylists = "https://api.spotify.com/v1/browse/featured-playlists";
        JSONObject jsonObjectFromResponse = getJsonObjectFromServerResponse(featuredPlaylists, accessToken);
        getPlaylist(jsonObjectFromResponse);
    }

    boolean categoryExists(String chosenCategory, List<String> listOfCategories) {
        for (String category : listOfCategories) {
            if (category.equalsIgnoreCase(chosenCategory)) {
                return true;
            }
        }
        System.out.println("Unknown category name.");
        return false;
    }

    public String getCategoryId(String accessToken, String chosenCategory, List<String> listOfCategories) {
        String categoryId = "";
        if (listOfCategories != null) {
            if (categoryExists(chosenCategory, listOfCategories)) {
                String responseFromServer = ClientServerHTTP.getAccessToChosenPartOfApp(accessToken, urlCategories);
                JSONObject categoryList = new JSONObject(responseFromServer);
                JSONObject categories = categoryList.getJSONObject("categories");
                JSONArray items = categories.getJSONArray("items");
                for (Object item : items) {
                    JSONObject currentCategory = (JSONObject) item;
                    String categoryName = currentCategory.getString("name");
                    if (categoryName.equalsIgnoreCase(chosenCategory)) {
                        categoryId = currentCategory.getString("id");
                    }
                }
            }
        }
        return categoryId;
    }

    public void getMoodPlaylist(String accessToken, String chosenCategory, List<String> listOfCategories) {
        String categoryId = getCategoryId(accessToken, chosenCategory, listOfCategories);
        if (categoryId.equals("")) {
            System.out.println("Chosen playlist doesn't exist or you don't check existing categories. Check categories list and then enter chosen playlist.");
        } else {
            StringBuilder playlistsUrl = new StringBuilder();
            playlistsUrl.append("https://api.spotify.com/v1/browse/categories/");
            playlistsUrl.append(categoryId);
            playlistsUrl.append("/playlists");
            JSONObject jsonObjectFromResponse = getJsonObjectFromServerResponse(playlistsUrl.toString(), accessToken);
            getPlaylist(jsonObjectFromResponse);
        }
    }
}