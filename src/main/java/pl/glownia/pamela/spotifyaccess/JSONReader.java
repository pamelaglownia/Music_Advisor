package pl.glownia.pamela.spotifyaccess;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class JSONReader {

    static String readAccessToken(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        return jsonObject.getString("access_token");
    }

    private static JSONObject getJsonObjectFromServerResponse(String accessToken, String url) {
        return new JSONObject(ClientServerHTTP.accessToChosenPartOfApp(accessToken, url));
    }

    static void readNewReleases(String accessToken) {
        JSONObject jsonObjectFromResponse = getJsonObjectFromServerResponse(accessToken, SpotifyUrl.getNewReleasesUrl());
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
        }
    }

    private static void readPlaylists(JSONObject jsonObjectFromResponse) {
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

    static void readFeaturedPlaylists(String accessToken) {
        readPlaylists(getJsonObjectFromServerResponse(accessToken, SpotifyUrl.getFeaturedPlaylistsUrl()));
    }

    static void readMoodPlaylists(String accessToken, String categoryId) {
        readPlaylists(getJsonObjectFromServerResponse(accessToken, SpotifyUrl.getMoodPlaylistUrl(categoryId)));
    }

    static List<String> readCategories(String accessToken) {
        JSONObject jsonObjectFromResponse = getJsonObjectFromServerResponse(accessToken, SpotifyUrl.getCategoriesUrl());
        JSONObject categories = jsonObjectFromResponse.getJSONObject("categories");
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

    static String readCategoryId(String accessToken, String chosenCategory) {
        String categoryId = "";
        JSONObject jsonObjectFromResponse = getJsonObjectFromServerResponse(accessToken, SpotifyUrl.getCategoriesUrl());
        JSONObject categories = jsonObjectFromResponse.getJSONObject("categories");
        JSONArray items = categories.getJSONArray("items");
        for (Object item : items) {
            JSONObject currentCategory = (JSONObject) item;
            String categoryName = currentCategory.getString("name");
            if (categoryName.equalsIgnoreCase(chosenCategory)) {
                categoryId = currentCategory.getString("id");
            }
        }
        return categoryId;
    }
}