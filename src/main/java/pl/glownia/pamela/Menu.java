package pl.glownia.pamela;

class Menu {
    Printer printer;
    Input input;

    Menu() {
        this.printer = new Printer();
        this.input = new Input();
    }

    void welcomeMenu() {
        Option chosenOption;
        System.out.println("What would you like to do? Choose one from the options below.");
        printer.printMainMenu();
        do {
            String userDecision = input.takeUserDecision();
            boolean isUserDecisionProper = Option.checkUserDecision(userDecision);
            while (!isUserDecisionProper) {
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
            }
        } while (!chosenOption.equals(Option.EXIT));
    }
}