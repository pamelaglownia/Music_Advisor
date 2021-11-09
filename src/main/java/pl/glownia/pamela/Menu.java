package pl.glownia.pamela;

import pl.glownia.pamela.spotifyaccess.Authorization;
import pl.glownia.pamela.spotifyaccess.SpotifyAccess;

class Menu {
    private final Input input;
    private final Authorization authorization;
    private String accessToken;
    private final SpotifyAccess spotifyAccess;

    Menu() {
        this.input = new Input();
        this.authorization = new Authorization();
        this.spotifyAccess = new SpotifyAccess();
    }

    void welcomeMenu() {
        Option chosenOption;
        Printer.printMainMenu();
        do {
            System.out.println("Choose option:");
            String userDecision = input.takeUserDecision();
            boolean isUserDecisionProper = Option.checkUserDecision(userDecision);
            while (!isUserDecisionProper || !authorization.isClientAuthorized() && !userDecision.equals("auth")) {
                if (!authorization.isClientAuthorized()) {
                    Printer.printInfoAboutAuthorization(authorization.isClientAuthorized());
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
                    spotifyAccess.printNewReleases(accessToken);
                    break;
                case FEATURED:
                    spotifyAccess.printFeatured(accessToken);
                    break;
                case CATEGORIES:
                    spotifyAccess.printCategories(accessToken);
                    break;
                case PLAYLISTS:
                    spotifyAccess.printMoodPlaylist(accessToken, input.getPlaylistName(userDecision));
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