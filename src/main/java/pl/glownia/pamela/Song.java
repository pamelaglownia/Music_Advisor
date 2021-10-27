package pl.glownia.pamela;

import java.util.Arrays;

class Song {
    private final String title;
    private final String[] artists;


    Song(String title, String[] artists) {
        this.title = title;
        this.artists = artists;
    }

    String[] getArtists() {
        return artists;
    }

    String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + " " + Arrays.toString(artists);
    }
}
