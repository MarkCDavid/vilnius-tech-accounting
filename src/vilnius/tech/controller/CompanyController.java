package vilnius.tech.controller;

import vilnius.tech.dal.Company;
import vilnius.tech.dal.ContactInformation;
import vilnius.tech.utils.UserInput;

import java.util.Objects;
import java.util.Scanner;

public class CompanyController extends Manager {

    public CompanyController() {
        this(new Company(), 0);
    }

    public CompanyController(Company source, int indentation) {
        this.source = source;
        this.indentation = indentation;
    }

    public Company create(Scanner scanner) {

        String name = UserInput.getString(scanner, "\t".repeat(indentation) + "Company name");

        System.out.println("\t".repeat(indentation) + "Contact Information:");
        ContactInformation contactInformation = new ContactInformationController(source,indentation + 1).read(scanner, true);

        source.setName(name);
        source.setContactInformation(contactInformation);
        return source;
    }


    @Override
    protected boolean isRoot() {
        return true;
    }

    private static final String INFO = "info";
    private static final String USERS = "users";
    private static final String CATEGORIES = "categories";
    private static final String CONTACTS = "contacts";
    private static final String ADDRESSES = "addresses";
    private static final String CITIES = "cities";
    private static final String COUNTRIES = "countries";
    private static final String EXPENSE_TYPES = "expense types";
    private static final String INCOME_TYPES = "income types";
    private static final String SAVE = "save";

    @Override
    protected void showOptions(Scanner scanner) {
        System.out.printf("%s - display company information%n", INFO);
        System.out.printf("%s - manage users%n", USERS);
        System.out.printf("%s - manage categories%n", CATEGORIES);
        System.out.printf("%s - manage contacts%n", CONTACTS);
        System.out.printf("%s - manage addresses%n", ADDRESSES);
        System.out.printf("%s - manage cities%n", CITIES);
        System.out.printf("%s - manage countries%n", COUNTRIES);
        System.out.printf("%s - manage expense types%n", EXPENSE_TYPES);
        System.out.printf("%s - manage income types%n", INCOME_TYPES);
        System.out.printf("%s - save company information%n", SAVE);
    }

    @Override
    protected void matchOptions(Scanner scanner, String userInput) {
        if(Objects.equals(userInput, INFO)) {
            System.out.println(source);
        }
        else if(Objects.equals(userInput, USERS)) {
            new UserController(source,indentation + 1).manage(scanner);
        }
        else if(Objects.equals(userInput, CATEGORIES)) {

        }
        else if(Objects.equals(userInput, CONTACTS)) {

        }
        else if(Objects.equals(userInput, ADDRESSES)) {

        }
        else if(Objects.equals(userInput, CITIES)) {

        }
        else if(Objects.equals(userInput, COUNTRIES)) {

        }
        else if(Objects.equals(userInput, EXPENSE_TYPES)) {

        }
        else if(Objects.equals(userInput, INCOME_TYPES)) {

        }
        else if(Objects.equals(userInput, SAVE)) {
            Serializer.saveCompany(scanner, source);
        }
    }

    private final Company source;
    private final int indentation;
}
