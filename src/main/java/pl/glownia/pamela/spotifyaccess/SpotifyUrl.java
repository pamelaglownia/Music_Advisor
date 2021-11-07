package pl.glownia.pamela.spotifyaccess;

class SpotifyUrl {

    static String getTokenUrl() {
        return "https://accounts.spotify.com/api/token";
    }

    static String getRedirectUri() {
        return "http://localhost:8080/user";
    }

    static String getAuthorizationLink(String userName) {
        System.out.println("Use this link to request the access code:");
        return "https://accounts.spotify.com/authorize?client_id=" +
                userName +
                "&redirect_uri=http://localhost:8080/user&response_type=code";
    }

    static String getCategoriesUrl() {
        return "https://api.spotify.com/v1/browse/categories";
    }

    static String getNewReleasesUrl() {
        return "https://api.spotify.com/v1/browse/new-releases";
    }

    static String getFeaturedPlaylistsUrl() {
        return "https://api.spotify.com/v1/browse/featured-playlists";
    }

    static String getMoodPlaylistUrl(String categoryId) {
        return "https://api.spotify.com/v1/browse/categories/" +
                categoryId +
                "/playlists";
    }
}