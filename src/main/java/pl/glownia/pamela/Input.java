package pl.glownia.pamela;

import java.util.Scanner;

class Input {
    Scanner scanner;
    Printer printer;

    Input() {
        this.scanner = new Scanner(System.in);
        this.printer = new Printer();
    }

    String takeUserDecision() {
        String userDecision = scanner.nextLine();
        while (userDecision.length() <= 1) {
            System.out.println("Wrong input. Enter again:");
            userDecision = scanner.nextLine().trim();
        }
        return userDecision;
    }
}