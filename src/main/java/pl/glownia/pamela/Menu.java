package pl.glownia.pamela;

import pl.glownia.pamela.spotifyaccess.SpotifyAccess;

class Menu {
    private final SpotifyAccess spotifyAccess;
    private final UserDecisionValidator validator;

    Menu() {
        this.spotifyAccess = new SpotifyAccess();
        this.validator = new UserDecisionValidator();
    }

    void chooseAction() {
        Option chosenOption;
        Printer.printMainMenu();
        do {
            System.out.println("Choose option:");
            String userDecision = validator.getProperUserDecision(spotifyAccess.isClientAuthorized());
            chosenOption = Option.printOption(userDecision);
            switch (chosenOption) {
                case NEW:
                    spotifyAccess.printNewReleases();
                    break;
                case FEATURED:
                    spotifyAccess.printFeatured();
                    break;
                case CATEGORIES:
                    spotifyAccess.printCategories();
                    break;
                case PLAYLISTS:
                    spotifyAccess.printMoodPlaylist(validator.getPlaylistName(userDecision));
                    break;
                case AUTH:
                    spotifyAccess.accessApp();
            }
        } while (!chosenOption.equals(Option.EXIT));
    }
}