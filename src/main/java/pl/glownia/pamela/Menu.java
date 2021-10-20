package pl.glownia.pamela;

class Menu {
    Printer printer;
    Input input;

    Menu() {
        this.printer = new Printer();
        this.input = new Input();
    }

    void welcomeMenu() {
        System.out.println("What would you like to do? Choose one from the options below.");
        printer.printMainMenu();
        String userDecision = input.takeUserDecision();
        boolean isUserDecisionProper = Option.checkUserDecision(userDecision);
        while (!isUserDecisionProper) {
            userDecision = input.takeUserDecision();
            isUserDecisionProper = Option.checkUserDecision(userDecision);
        }
    }
}