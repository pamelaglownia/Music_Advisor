package pl.glownia.pamela;

import pl.glownia.pamela.spotifyaccess.Authorization;
import pl.glownia.pamela.spotifyaccess.SpotifyAccess;

import java.util.List;

class Menu {
    Printer printer;
    Input input;
    Authorization authorization;
    Option chosenOption;
    String accessToken;
    private List<String> listOfCategories;

    Menu() {
        this.printer = new Printer();
        this.input = new Input();
        this.authorization = new Authorization();
    }

    void welcomeMenu() {
        printer.printMainMenu();
        do {
            System.out.println("\nChoose option:");
            String userDecision = input.takeUserDecision();
            boolean isUserDecisionProper = Option.checkUserDecision(userDecision);
            while (!isUserDecisionProper || !authorization.isClientAuthorized() && !userDecision.equals("auth")) {
                if (!authorization.isClientAuthorized()) {
                    printer.printInfoAboutAuthorization(authorization.isClientAuthorized());
                    if (userDecision.equals("exit")) {
                        break;
                    }
                }
                userDecision = input.takeUserDecision();
                isUserDecisionProper = Option.checkUserDecision(userDecision);
            }
            chosenOption = Option.printOption(userDecision);
            switch (chosenOption) {
                case NEW:
                    SpotifyAccess.getNewReleases(accessToken);
                    break;
                case FEATURED:
                    SpotifyAccess.getFeaturedPlaylists(accessToken);
                    break;
                case CATEGORIES:
                    listOfCategories = SpotifyAccess.getCategories(accessToken);
                    break;
                case PLAYLISTS:
                    SpotifyAccess.getMoodPlaylist(accessToken, input.getPlaylistName(userDecision), listOfCategories);
                    break;
                case AUTH:
                    if (!authorization.isClientAuthorized()) {
                        accessToken = authorization.accessApp();
                    } else {
                        System.out.println("You've already accessed.");
                    }
            }
        } while (!chosenOption.equals(Option.EXIT));
    }
}