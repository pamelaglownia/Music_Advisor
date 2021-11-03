package pl.glownia.pamela;

import pl.glownia.pamela.clientserverhttp.Authorization;
import pl.glownia.pamela.demo.DemoApp;

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
                    demoApp.printSongs();
                    break;
                case FEATURED:
                    demoApp.printFeatured();
                    break;
                case CATEGORIES:
                    demoApp.printCategories();
                    break;
                case PLAYLISTS:
                    demoApp.printMoodList();
                    break;
                case AUTH:
                    if (!authorization.isClientAuthorized()) {
                        authorization.accessApp();
//                        printer.printInfoAboutAuthorization(authorization.isAuthorized);
                    } else {
                        System.out.println("You've already accessed.");
                    }
            }
        } while (!chosenOption.equals(Option.EXIT));
    }
}