package pl.glownia.pamela.spotifyaccess;

import java.util.ArrayList;
import java.util.List;

public class SpotifyAccess {
    private final JSONReader jsonReader;
    private final Authorization authorization;
    private String accessToken;

    public SpotifyAccess() {
        jsonReader = new JSONReader();
        authorization = new Authorization();
    }

    public String accessApp() {
        if (!isClientAuthorized()) {
            accessToken = authorization.accessApp();
        }
        if (accessToken == null) {
            return "exit";
        }
        return accessToken;
    }

    public boolean isClientAuthorized() {
        return authorization.isClientAuthorized();
    }

    private List<NewReleases> getNewReleases() {
        return jsonReader.readNewReleases(accessToken);
    }

    public void printNewReleases() {
        List<NewReleases> newReleases = getNewReleases();
        newReleases.forEach(System.out::println);
    }

    private List<Playlist> getFeaturedPlaylists() {
        return jsonReader.readFeaturedPlaylists(accessToken);
    }

    public void printFeatured() {
        List<Playlist> featured = getFeaturedPlaylists();
        featured.forEach(System.out::println);
    }

    private List<String> getCategories() {
        return jsonReader.readCategories(accessToken);
    }

    public void printCategories() {
        List<String> categories = getCategories();
        categories.forEach(System.out::println);
        System.out.println();
    }

    private boolean categoryExists(String chosenCategory) {
        List<String> categories = getCategories();
        for (String category : categories) {
            if (category.equalsIgnoreCase(chosenCategory)) {
                return true;
            }
        }
        return false;
    }

    private String getCategoryId(String chosenCategory) {
        List<String> categories = getCategories();
        String categoryId = "";
        if (categories != null) {
            if (categoryExists(chosenCategory)) {
                categoryId = jsonReader.readCategoryId(accessToken, chosenCategory);
            } else {
                System.out.println("Chosen playlists don't exist. Check categories list and then enter chosen playlists.");
            }
        }
        return categoryId;
    }

    private List<Playlist> getMoodPlaylist(String chosenCategory) {
        List<Playlist> moodPlaylist = new ArrayList<>();
        String categoryId = getCategoryId(chosenCategory);
        if (!categoryId.equals("")) {
            moodPlaylist = jsonReader.readMoodPlaylists(accessToken, categoryId);
        }
        return moodPlaylist;
    }

    public void printMoodPlaylist(String chosenCategory) {
        List<Playlist> moodPlaylist = getMoodPlaylist(chosenCategory);
        if (!moodPlaylist.isEmpty()) {
            moodPlaylist.forEach(System.out::println);
        }
    }
}