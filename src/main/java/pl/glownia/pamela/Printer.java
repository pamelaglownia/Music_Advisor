package pl.glownia.pamela;

class Printer {

    static void printMainMenu() {
        System.out.println("What would you like to do? Choose one from the options below.");
        System.out.println("auth - to access app");
        System.out.println("new - to see list of new albums on Spotify");
        System.out.println("featured - to see list of Spotify-featured playlist");
        System.out.println("categories - a list of all available categories on Spotify");
        System.out.println("playlists + it's name - playlist of chosen category");
        System.out.println("exit - to shut down the application");
    }
}