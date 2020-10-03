package vilnius.tech.controller;

import vilnius.tech.utils.UserInput;

import java.util.Scanner;

public abstract class Manager {

    public void manage(Scanner scanner) {
        while(true) {
            showOptions(scanner);
            System.out.printf("quit - %s%n", getQuitDescription());
            String userInput = UserInput.getString(scanner, "command");
            if(userInput.toLowerCase().equals("quit"))
                break;
            matchOptions(scanner, userInput);
        }
    }

    private String getQuitDescription() {
        return isRoot() ? "quit application" : "go back";
    }

    protected abstract boolean isRoot();

    protected abstract void showOptions(Scanner scanner);
    protected abstract void matchOptions(Scanner scanner, String userInput);
}
