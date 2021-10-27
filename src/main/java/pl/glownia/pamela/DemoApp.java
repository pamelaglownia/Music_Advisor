package pl.glownia.pamela;

import java.util.ArrayList;
import java.util.List;

class DemoApp {
    private final List<Song> songs = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();
    private final List<Featured> featured = new ArrayList<>();
    private final List<MoodPlaylist> moodPlaylist = new ArrayList<>();

    String getClientId() {
        return "df36e5b6fbff48f6946be56ece7a9ef1";
    }

    List<Song> createSongsList() {
        songs.add(new Song("Mountains", new String[]{"Sia", "Diplo", "Labrinth"}));
        songs.add(new Song("Runaway", new String[]{"Lil Peep"}));
        songs.add(new Song("The Greatest Show", new String[]{"Panic! At The Disco"}));
        songs.add(new Song("All Out Life", new String[]{"Slipknot"}));
        return songs;
    }

    List<Category> createCategories() {
        categories.add(new Category("Top Lists"));
        categories.add(new Category("Pop"));
        categories.add(new Category("Mood"));
        categories.add(new Category("Latin"));
        return categories;
    }

    List<Featured> createFeatured() {
        featured.add(new Featured("Mellow Morning"));
        featured.add(new Featured("Wake Up and Smell the Coffee"));
        featured.add(new Featured("Monday Motivation"));
        featured.add(new Featured("Songs to Sing in the Shower"));
        return featured;
    }

    List<MoodPlaylist> createMoodPlaylist() {
        moodPlaylist.add(new MoodPlaylist("Walk Like A Badass"));
        moodPlaylist.add(new MoodPlaylist("Rage Beats"));
        moodPlaylist.add(new MoodPlaylist("Arab Mood Booster"));
        moodPlaylist.add(new MoodPlaylist("Sunday Stroll"));
        return moodPlaylist;
    }
}