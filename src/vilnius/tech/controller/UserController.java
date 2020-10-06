package vilnius.tech.controller;

import vilnius.tech.dal.JuridicalUser;
import vilnius.tech.dal.PhysicalUser;
import vilnius.tech.dal.Session;
import vilnius.tech.dal.User;
import vilnius.tech.utils.UserInput;

import java.util.Objects;
import java.util.Scanner;

public class UserController extends Manager {

    public UserController(Session session) {
        super(session);
    }

    @Override
    protected boolean isRoot() {
        return false;
    }

    @Override
    protected void showOptions(Scanner scanner) {
        System.out.printf("%s - manage physical users%n", PhysicalUser.CODE);
        System.out.printf("%s - manage juridical users%n", JuridicalUser.CODE);
    }

    public User read(Scanner scanner) {
        System.out.printf("%s - manage physical users%n", PhysicalUser.CODE);
        System.out.printf("%s - manage juridical users%n", JuridicalUser.CODE);

        System.out.printf("quit - %s%n", getQuitDescription());
        String userInput = UserInput.getString(scanner, "command");
        if(userInput.equalsIgnoreCase("quit"))
            return null;

        if(Objects.equals(userInput, PhysicalUser.CODE)) {
            return new PhysicalUserController(getSession()).read(scanner);
        }

        else if(Objects.equals(userInput, JuridicalUser.CODE)) {
            return new JuridicalUserController(getSession()).read(scanner);
        }

        return null;
    }

    @Override
    protected void matchOptions(Scanner scanner, String userInput) {
        if(Objects.equals(userInput, PhysicalUser.CODE)) {
            new PhysicalUserController(getSession()).manage(scanner);
        }
        else if(Objects.equals(userInput, JuridicalUser.CODE)) {
            new JuridicalUserController(getSession()).manage(scanner);
        }
    }
}
