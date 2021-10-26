package pl.glownia.pamela;

enum Option {
    FEATURED("---FEATURED---"),
    NEW("---NEW RELEASES---"),
    CATEGORIES("---CATEGORIES---"),
    PLAYLISTS(" PLAYLISTS---"),
    AUTH(""),
    EXIT("---GOODBYE!---");

    String text;

    Option(String text) {
        this.text = text;
    }

    static boolean checkUserDecision(String userDecision) {
        if (userDecision.contains("playlists")) {
            String[] userArrayDecision = userDecision.split(" ");
            if (userArrayDecision.length < 2) {
                userDecision = "";
                System.out.println("(You forgot about playlist name...)");
            } else {
                userDecision = userArrayDecision[0];
            }
        }
        for (Option option : Option.values()) {
            if (option.name().toLowerCase().equals(userDecision)) {
                return true;
            }
        }
        System.out.println("Wrong input. Try again.");
        return false;
    }

    static Option printOption(String userDecision) {
        Option chosenOption = EXIT;
        if (userDecision.contains("playlists")) {
            String[] userArrayDecision = userDecision.split(" ");
            System.out.println("---" + userArrayDecision[1].toUpperCase() + PLAYLISTS.text);
            chosenOption = PLAYLISTS;
        }
        for (Option option : Option.values()) {
            if (option.name().toLowerCase().equals(userDecision)) {
                System.out.println(option.text);
                chosenOption = option;
            }
        }
        return chosenOption;
    }
}