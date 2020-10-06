package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Scanner;

public class PhysicalUserController extends CRUDManager<PhysicalUser> {

    public PhysicalUserController(Session session, int indentation) {
        super(session);
        this.indentation = indentation;
    }

    @Override
    public PhysicalUser create(Scanner scanner) {
        String username = UserInput.getString(scanner, "\t".repeat(indentation) + "Username");
        String password = UserInput.getString(scanner, "\t".repeat(indentation) + "Password");
        String name = UserInput.getString(scanner, "\t".repeat(indentation) + "Name");
        String surname = UserInput.getString(scanner, "\t".repeat(indentation) + "Surname");

        System.out.println("\t".repeat(indentation) + "Contact Information:");
        ContactInformation contactInformation = new ContactInformationController(getSession(),indentation + 1).read(scanner, true);
        PhysicalUser physicalUser = new PhysicalUser(getSession());

        physicalUser.setUsername(username);
        physicalUser.setPassword(password);
        physicalUser.setName(name);
        physicalUser.setSurname(surname);
        physicalUser.setContactInformation(contactInformation);


        return physicalUser;
    }

    @Override
    public PhysicalUser read(Scanner scanner, boolean create) {
        return create ? Selector.readViaOidOrCreate(this, scanner) : Selector.readViaOid(this, scanner);
    }

    @Override
    public PhysicalUser read(Scanner scanner) {
        return read(scanner, false);
    }

    @Override
    public List<PhysicalUser> readAll() {
        return getSession().get(PhysicalUser.class);
    }

    @Override
    public PhysicalUser update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    private final int indentation;

    private static final String OBJECT_NAME = "Physical User";

    @Override
    protected String getManagedObjectName() {
        return OBJECT_NAME;
    }
}
