package pl.glownia.pamela.spotifyaccess;

class Playlist {

    private final String name;
    private final String url;

    Playlist(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return name + "\n" + url + "\n";
    }
}