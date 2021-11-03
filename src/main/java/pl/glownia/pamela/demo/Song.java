package pl.glownia.pamela.demo;

import java.util.Arrays;

class Song {
    private final String title;
    private final String[] artists;

    Song(String title, String[] artists) {
        this.title = title;
        this.artists = artists;
    }

    @Override
    public String toString() {
        return title + " " + Arrays.toString(artists);
    }
}
