package vilnius.tech.controller;

import vilnius.tech.dal.JuridicalUser;
import vilnius.tech.dal.PhysicalUser;
import vilnius.tech.dal.Session;

import java.util.Objects;
import java.util.Scanner;

public class UserController extends Manager {

    public UserController(Session session, int indentation) {
        super(session);
        this.indentation = indentation;
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

    @Override
    protected void matchOptions(Scanner scanner, String userInput) {
        if(Objects.equals(userInput, PhysicalUser.CODE)) {
            new PhysicalUserController(getSession(), indentation).manage(scanner);
        }
        else if(Objects.equals(userInput, JuridicalUser.CODE)) {
            new JuridicalUserController(getSession(), indentation).manage(scanner);
        }
    }

    private final int indentation;
}
