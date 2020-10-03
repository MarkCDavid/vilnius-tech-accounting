package vilnius.tech.controller;

import vilnius.tech.dal.Company;
import vilnius.tech.dal.JuridicalUser;
import vilnius.tech.dal.PhysicalUser;

import java.util.Objects;
import java.util.Scanner;

public class UserController extends Manager {

    public UserController(Company source, int indentation) {
        this.source = source;
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
            new PhysicalUserController(source, indentation).manage(scanner);
        }
        else if(Objects.equals(userInput, JuridicalUser.CODE)) {
            new JuridicalUserController(source,indentation).manage(scanner);
        }
    }

    private final Company source;
    private final int indentation;
}
