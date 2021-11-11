package pl.glownia.pamela;

import pl.glownia.pamela.spotifyaccess.Authorization;
import pl.glownia.pamela.spotifyaccess.SpotifyAccess;

class Menu {
    private final Authorization authorization;
    private String accessToken;
    private final SpotifyAccess spotifyAccess;
    private final UserDecisionValidator validator;

    Menu() {
        this.authorization = new Authorization();
        this.spotifyAccess = new SpotifyAccess();
        this.validator = new UserDecisionValidator();
    }

    void chooseAction() {
        Option chosenOption;
        Printer.printMainMenu();
        do {
            System.out.println("Choose option:");
            String userDecision = validator.getProperUserDecision(authorization.isClientAuthorized());
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
                    spotifyAccess.printMoodPlaylist(accessToken, validator.getPlaylistName(userDecision));
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