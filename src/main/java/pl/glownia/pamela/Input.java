package pl.glownia.pamela;

import java.util.Scanner;

class Input {
    Scanner scanner = new Scanner(System.in);

    String takeUserDecision() {
        String userDecision = scanner.nextLine();
        while (userDecision.length() <= 1) {
            System.out.println("Wrong input. Enter again:");
            userDecision = scanner.nextLine().trim();
        }
        return userDecision;
    }
}