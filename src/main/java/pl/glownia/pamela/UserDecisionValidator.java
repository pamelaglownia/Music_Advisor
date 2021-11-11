package pl.glownia.pamela;

class UserDecisionValidator {

    private String takeDecision() {
        return new Input().takeUserDecision();
    }

    private boolean isProperInput(String userDecision) {
        return Option.checkUserDecision(userDecision);
    }

    private boolean isAlreadyAuthorized(String userDecision, boolean isAuthorized) {
        if (!isAuthorized && !userDecision.equals("auth")) {
            System.out.println("Please, provide access for application.");
            return false;
        }
        return true;
    }

    String getProperUserDecision(boolean isAuthorized) {
        String userDecision = takeDecision();
        while (!isProperInput(userDecision) || !isAlreadyAuthorized(userDecision, isAuthorized)) {
            userDecision = takeDecision();
            if (userDecision.equalsIgnoreCase("exit")) {
                break;
            }
        }
        while (isAuthorized && userDecision.equalsIgnoreCase("auth")) {
            System.out.println("You've already accessed. Choose other option:");
            userDecision = takeDecision();
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
