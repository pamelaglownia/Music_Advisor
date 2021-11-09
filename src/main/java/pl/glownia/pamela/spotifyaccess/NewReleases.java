package pl.glownia.pamela.spotifyaccess;

import java.util.Arrays;

class NewReleases {
    private final String name;
    private final String[] artists;
    private final String url;

    NewReleases(String name, String[] artists, String url) {
        this.name = name;
        this.artists = artists;
        this.url = url;
    }

    @Override
    public String toString() {
        return name.toUpperCase() + "\n" + Arrays.toString(artists) + "\n" + url + "\n";
    }
}
