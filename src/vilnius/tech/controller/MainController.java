package vilnius.tech.controller;

import vilnius.tech.dal.Company;
import vilnius.tech.dal.Session;

import java.util.Objects;
import java.util.Scanner;

public class MainController extends Manager {

    public MainController() {
        this(new Session());
    }

    public MainController(Session session) {
        super(session);
        this.source = new Company(session);
    }

    public Company create(Scanner scanner) {

//        String name = UserInput.getString(scanner, "\t".repeat(indentation) + "Company name");
//
//        System.out.println("\t".repeat(indentation) + "Contact Information:");
//        ContactInformation contactInformation = new ContactInformationController(source,indentation + 1).read(scanner, true);
//
//        source.setName(name);
//        source.setContactInformation(contactInformation);
//        return source;
        return null;
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
            new UserController(getSession()).manage(scanner);
        }
        else if(Objects.equals(userInput, CATEGORIES)) {

        }
        else if(Objects.equals(userInput, CONTACTS)) {
            new ContactInformationController(getSession()).manage(scanner);
        }
        else if(Objects.equals(userInput, ADDRESSES)) {
            new AddressController(getSession()).manage(scanner);
        }
        else if(Objects.equals(userInput, CITIES)) {
            new CityController(getSession()).manage(scanner);
        }
        else if(Objects.equals(userInput, COUNTRIES)) {
            new CountryController(getSession()).manage(scanner);
        }
        else if(Objects.equals(userInput, EXPENSE_TYPES)) {
            new ExpenseTypeController(getSession()).manage(scanner);
        }
        else if(Objects.equals(userInput, INCOME_TYPES)) {
            new IncomeTypeController(getSession()).manage(scanner);
        }
        else if(Objects.equals(userInput, SAVE)) {
            Serializer.saveSession(scanner, getSession());
        }
    }

    private final Company source;
}
