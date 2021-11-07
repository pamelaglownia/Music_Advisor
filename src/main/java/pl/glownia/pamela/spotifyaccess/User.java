package pl.glownia.pamela.spotifyaccess;

class User {
    private final String name;
    private final String password;

    User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    String getName() {
        return name;
    }

    String getPassword() {
        return password;
    }

}
