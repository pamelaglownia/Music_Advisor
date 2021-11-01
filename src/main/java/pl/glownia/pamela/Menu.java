package pl.glownia.pamela;

class Menu {
    Printer printer;
    Input input;
    Authorization authorization;
    Option chosenOption;
    DemoApp demoApp;

    Menu() {
        this.printer = new Printer();
        this.input = new Input();
        this.demoApp = new DemoApp();
        this.authorization = new Authorization();
    }

    void welcomeMenu() {
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
                    printer.printNewRelease(demoApp.createSongsList());
                    break;
                case FEATURED:
                    printer.printFeatured(demoApp.createFeatured());
                    break;
                case CATEGORIES:
                    printer.printCategories(demoApp.createCategories());
                    break;
                case PLAYLISTS:
                    printer.printMoodPlaylist(demoApp.createMoodPlaylist());
                    break;
                case AUTH:
                    if (!authorization.isAuthorized) {
                        authorization.accessApp();
//                        printer.printInfoAboutAuthorization(authorization.isAuthorized);
                    } else {
                        System.out.println("You've already accessed.");
                    }
            }
        } while (!chosenOption.equals(Option.EXIT));
    }
}