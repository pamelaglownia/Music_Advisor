package pl.glownia.pamela;

import java.util.List;

class Printer {

    void printMainMenu() {
        System.out.println("What would you like to do? Choose one from the options below.");
        System.out.println("auth - to access app");
        System.out.println("new - to see list of new albums on Spotify");
        System.out.println("featured - to see list of Spotify-featured playlist");
        System.out.println("categories - a list of all available categories on Spotify");
        System.out.println("playlists + it's name - playlist of chosen category");
        System.out.println("exit - to shut down the application");
    }

    void printNewRelease(List<Song> songs) {
        songs.forEach(System.out::println);
    }

    void printFeatured(List<Featured> featured) {
        featured.forEach(System.out::println);
    }

    void printCategories(List<Category> categories) {
        categories.forEach(System.out::println);
    }

    void printMoodPlaylist(List<MoodPlaylist> moodPlaylists) {
        moodPlaylists.forEach(System.out::println);
    }

    void printInfoAboutAuthorization(boolean isAuthorized) {
        if (isAuthorized) {
            System.out.println("---SUCCESS---");
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}