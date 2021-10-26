package pl.glownia.pamela;

import java.util.HashMap;
import java.util.Map;

class Printer {

    void printMainMenu() {
        System.out.println("auth - to access app");
        System.out.println("new - to see list of new albums on Spotify");
        System.out.println("featured - to see list of Spotify-featured playlist");
        System.out.println("categories - a list of all available categories on Spotify");
        System.out.println("playlists + it's name - playlist of chosen category");
        System.out.println("exit - to shut down the application");
    }

    void printNewRelease() {
        Map<String, String> songs = new HashMap<>();
        songs.put("Mountains", "[Sia, Diplo, Labrinth]");
        songs.put("Runaway", "[Lil Peep]");
        songs.put("The Greatest Show", "[Panic! At The Disco]");
        songs.put("All Out Life", "[Slipknot]");
        songs.forEach((key, value) -> System.out.println(key + " " + value));
    }

    void printFeatured() {
        System.out.println("Mellow Morning\n" +
                "Wake Up and Smell the Coffee\n" +
                "Monday Motivation\n" +
                "Songs to Sing in the Shower");
    }

    void printCategories() {
        System.out.println("Top Lists\n" +
                "Pop\n" +
                "Mood\n" +
                "Latin");
    }

    void printMoodPlaylist() {
        System.out.println("Walk Like A Badass  \n" +
                "Rage Beats  \n" +
                "Arab Mood Booster  \n" +
                "Sunday Stroll");
    }

    void printInfoAboutAuthorization(boolean isAuthorized) {
        if (isAuthorized) {
            System.out.println("---SUCCESS---");
        } else {
            System.out.println("Please, provide access for application.");
        }
    }
}