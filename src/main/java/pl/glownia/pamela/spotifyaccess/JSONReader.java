package pl.glownia.pamela.spotifyaccess;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class JSONReader {
    private final List<NewReleases> newReleases = new ArrayList<>();
    private final List<String> categoriesList = new ArrayList<>();
    private final List<Playlist> chosenPlaylist = new ArrayList<>();

    static String readAccessToken(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        return jsonObject.getString("access_token");
    }

    private JSONObject getJsonObjectFromServerResponse(String accessToken, String url) {
        return new JSONObject(ClientServerHTTP.accessToChosenPartOfApp(accessToken, url));
    }

    List<NewReleases> readNewReleases(String accessToken) {
        String albumName = "";
        JSONObject jsonObjectFromResponse = getJsonObjectFromServerResponse(accessToken, SpotifyUrl.getNewReleasesUrl());
        JSONObject albums = jsonObjectFromResponse.getJSONObject("albums");
        JSONArray albumsItems = albums.getJSONArray("items");
        for (Object item : albumsItems) {
            JSONObject albumObject = (JSONObject) item;
            albumName = albumObject.getString("name");
            JSONArray artists = albumObject.getJSONArray("artists");
            List<String> artistsToPrint = new ArrayList<>();
            for (Object artist : artists) {
                JSONObject currentArtist = (JSONObject) artist;
                String artistName = currentArtist.getString("name");
                artistsToPrint.add(artistName);
            }
            JSONObject externalUrl = albumObject.getJSONObject("external_urls");
            String albumUrl = externalUrl.getString("spotify");
            String[] artistsArray = artistsToPrint.toArray(String[]::new);
            newReleases.add(new NewReleases(albumName, artistsArray, albumUrl));
        }
        return newReleases;
    }

    private List<Playlist> readPlaylists(JSONObject jsonObjectFromResponse) {
        JSONObject playlists = jsonObjectFromResponse.getJSONObject("playlists");
        JSONArray playlistsItems = playlists.getJSONArray("items");
        for (Object playlist : playlistsItems) {
            JSONObject currentPlaylist = (JSONObject) playlist;
            String playlistName = currentPlaylist.getString("name");
            JSONObject externalUrl = currentPlaylist.getJSONObject("external_urls");
            String url = externalUrl.getString("spotify");
            chosenPlaylist.add(new Playlist(playlistName, url));
        }
        return chosenPlaylist;
    }

    List<Playlist> readFeaturedPlaylists(String accessToken) {
        return readPlaylists(getJsonObjectFromServerResponse(accessToken, SpotifyUrl.getFeaturedPlaylistsUrl()));
    }

    List<Playlist> readMoodPlaylists(String accessToken, String categoryId) {
        return readPlaylists(getJsonObjectFromServerResponse(accessToken, SpotifyUrl.getMoodPlaylistUrl(categoryId)));
    }

    List<String> readCategories(String accessToken) {
        JSONObject jsonObjectFromResponse = getJsonObjectFromServerResponse(accessToken, SpotifyUrl.getCategoriesUrl());
        JSONObject categories = jsonObjectFromResponse.getJSONObject("categories");
        JSONArray categoryItems = categories.getJSONArray("items");
        for (Object category : categoryItems) {
            JSONObject currentCategory = (JSONObject) category;
            String nameOfCategory = currentCategory.getString("name");
            categoriesList.add(nameOfCategory);
        }
        return categoriesList;
    }

    String readCategoryId(String accessToken, String chosenCategory) {
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