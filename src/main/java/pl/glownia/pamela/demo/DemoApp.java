package pl.glownia.pamela.demo;

import java.util.ArrayList;
import java.util.List;

public class DemoApp {
    private List<Song> songs = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<Featured> featured = new ArrayList<>();
    private List<MoodPlaylist> moodPlaylist = new ArrayList<>();

    List<Song> createSongsList() {
        songs.add(new Song("Mountains", new String[]{"Sia", "Diplo", "Labrinth"}));
        songs.add(new Song("Runaway", new String[]{"Lil Peep"}));
        songs.add(new Song("The Greatest Show", new String[]{"Panic! At The Disco"}));
        songs.add(new Song("All Out Life", new String[]{"Slipknot"}));
        return songs;
    }

    public void printSongs() {
        songs = createSongsList();
        songs.forEach(System.out::println);
    }

    List<Category> createCategories() {
        categories.add(new Category("Top Lists"));
        categories.add(new Category("Pop"));
        categories.add(new Category("Mood"));
        categories.add(new Category("Latin"));
        categories.forEach(System.out::println);
        return categories;
    }

    public void printCategories() {
        categories = createCategories();
        categories.forEach(System.out::println);
    }

    List<Featured> createFeatured() {
        featured.add(new Featured("Mellow Morning"));
        featured.add(new Featured("Wake Up and Smell the Coffee"));
        featured.add(new Featured("Monday Motivation"));
        featured.add(new Featured("Songs to Sing in the Shower"));
        return featured;
    }

    public void printFeatured() {
        featured = createFeatured();
        featured.forEach(System.out::println);
    }

    List<MoodPlaylist> createMoodPlaylist() {
        moodPlaylist.add(new MoodPlaylist("Walk Like A Badass"));
        moodPlaylist.add(new MoodPlaylist("Rage Beats"));
        moodPlaylist.add(new MoodPlaylist("Arab Mood Booster"));
        moodPlaylist.add(new MoodPlaylist("Sunday Stroll"));
        return moodPlaylist;
    }

    public void printMoodList() {
        moodPlaylist = createMoodPlaylist();
        moodPlaylist.forEach(System.out::println);
    }
}