package pl.glownia.pamela;

class Menu {
    Printer printer;
    Input input;

    Menu() {
        this.printer = new Printer();
        this.input = new Input();
    }

    void welcomeMenu() {
        String clientId = "df36e5b6fbff48f6946be56ece7a9ef1";
        Authorization authorization = new Authorization(clientId);
        Option chosenOption;
        System.out.println("What would you like to do? Choose one from the options below.");
        printer.printMainMenu();
        do {
            String userDecision = input.takeUserDecision();
            boolean isUserDecisionProper = Option.checkUserDecision(userDecision);
            while (!isUserDecisionProper || !authorization.isClientAuthorized() && !userDecision.equals("auth")) {
                if (!authorization.isClientAuthorized()) {
                    printer.printInfoAboutAuthorization(authorization.isAuthorized);
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
                    printer.printNewRelease();
                    break;
                case FEATURED:
                    printer.printFeatured();
                    break;
                case CATEGORIES:
                    printer.printCategories();
                    break;
                case PLAYLISTS:
                    printer.printMoodPlaylist();
                    break;
                case AUTH:
                    if (!authorization.isAuthorized) {
                        authorization.accessApp();
                        printer.printInfoAboutAuthorization(authorization.isAuthorized);
                    } else {
                        System.out.println("You've already accessed.");
                    }
            }
        } while (!chosenOption.equals(Option.EXIT));
    }
}