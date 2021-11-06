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

    String getPlaylistName(String userDecision) {
        String[] arrayWithPlaylistCategory = userDecision.split(" ");
        StringBuilder chosenCategory = new StringBuilder();
        for (int i = 1; i < arrayWithPlaylistCategory.length; i++) {
            chosenCategory.append(arrayWithPlaylistCategory[i]).append(" ");
        }
        return chosenCategory.toString().trim();

    }
}