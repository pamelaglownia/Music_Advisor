package pl.glownia.pamela.spotifyaccess;

import java.util.ArrayList;
import java.util.List;

public class SpotifyAccess {
    private List<Playlist> moodPlaylist = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    JSONReader jsonReader = new JSONReader();

    private List<NewReleases> getNewReleases(String accessToken) {
        return jsonReader.readNewReleases(accessToken);
    }

    public void printNewReleases(String accessToken) {
        List<NewReleases> newReleases = getNewReleases(accessToken);
        newReleases.forEach(System.out::println);
    }

    private List<Playlist> getFeaturedPlaylists(String accessToken) {
        return jsonReader.readFeaturedPlaylists(accessToken);
    }

    public void printFeatured(String accessToken) {
        List<Playlist> featured = getFeaturedPlaylists(accessToken);
        featured.forEach(System.out::println);
    }

    private List<String> getCategories(String accessToken) {
        return jsonReader.readCategories(accessToken);
    }

    public void printCategories(String accessToken) {
        categories = getCategories(accessToken);
        categories.forEach(System.out::println);
        System.out.println();
    }

    private boolean categoryExists(String accessToken, String chosenCategory) {
        categories = getCategories(accessToken);
        for (String category : categories) {
            if (category.equalsIgnoreCase(chosenCategory)) {
                return true;
            }
        }
        System.out.println("Unknown category name.");
        return false;
    }

    private String getCategoryId(String accessToken, String chosenCategory) {
        String categoryId = "";
        if (categories != null) {
            if (categoryExists(accessToken, chosenCategory)) {
                categoryId = jsonReader.readCategoryId(accessToken, chosenCategory);
            }
        }
        return categoryId;
    }

    private List<Playlist> getMoodPlaylist(String accessToken, String chosenCategory) {
        String categoryId = getCategoryId(accessToken, chosenCategory);
        if (categoryId.equals("")) {
            System.out.println("Chosen playlists don't exist. Check categories list and then enter chosen playlists.");
        } else {
            moodPlaylist = jsonReader.readMoodPlaylists(accessToken, categoryId);
        }
        return moodPlaylist;
    }

    public void printMoodPlaylist(String accessToken, String chosenCategory) {
        moodPlaylist = getMoodPlaylist(accessToken, chosenCategory);
        moodPlaylist.forEach(System.out::println);
    }
}