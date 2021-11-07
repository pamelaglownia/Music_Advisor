package pl.glownia.pamela.spotifyaccess;

import java.util.List;

public class SpotifyAccess {

    public static void getNewReleases(String accessToken) {
        JSONReader.readNewReleases(accessToken);
    }

    public static void getFeaturedPlaylists(String accessToken) {
        JSONReader.readFeaturedPlaylists(accessToken);
    }

    public static List<String> getCategories(String accessToken) {
        return JSONReader.readCategories(accessToken);
    }

    private static boolean categoryExists(String chosenCategory, List<String> listOfCategories) {
        for (String category : listOfCategories) {
            if (category.equalsIgnoreCase(chosenCategory)) {
                return true;
            }
        }
        System.out.println("Unknown category name.");
        return false;
    }

    private static String getCategoryId(String accessToken, String chosenCategory, List<String> listOfCategories) {
        String categoryId = "";
        if (listOfCategories != null) {
            if (categoryExists(chosenCategory, listOfCategories)) {
                categoryId = JSONReader.readCategoryId(accessToken, chosenCategory);
            }
        }
        return categoryId;
    }

    public static void getMoodPlaylist(String accessToken, String chosenCategory, List<String> listOfCategories) {
        String categoryId = getCategoryId(accessToken, chosenCategory, listOfCategories);
        if (categoryId.equals("")) {
            System.out.println("Chosen playlists don't exist or you don't check existing categories. Check categories list and then enter chosen playlists.");
        } else {
            JSONReader.readMoodPlaylists(accessToken, categoryId);
        }
    }
}