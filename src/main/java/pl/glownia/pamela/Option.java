package pl.glownia.pamela;

enum Option {
    FEATURED("---FEATURED---"),
    NEW("---NEW RELEASES---"),
    CATEGORIES("---CATEGORIES---"),
    PLAYLISTS(" PLAYLISTS---"),
    EXIT("---GOODBYE!---");

    String text;

    Option(String text) {
        this.text = text;
    }

    static boolean checkUserDecision(String userDecision) {
        if (userDecision.contains("playlists")) {
            String[] userArrayDecision = userDecision.split(" ");
            System.out.println("---" + userArrayDecision[1].toUpperCase() + PLAYLISTS.text);
            return true;
        } else {
            for (Option option : Option.values()) {
                if (option.name().toLowerCase().equals(userDecision)) {
                    System.out.println(option.text);
                    return true;
                }
            }
        }
        System.out.println("Wrong input. Try again.");
        return false;
    }
}