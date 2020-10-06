package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Scanner;

public class JuridicalUserController extends CRUDManager<JuridicalUser> {

    public JuridicalUserController(Session session) {
        super(session);
    }


    @Override
    public JuridicalUser create(Scanner scanner) {
        String username = UserInput.getString(scanner, "Username");
        String password = UserInput.getString(scanner, "Password");
        String name = UserInput.getString(scanner, "Name");

        System.out.println("Address:");
        Address address = new AddressController(getSession()).read(scanner, true);

        System.out.println("Contact:");
        PhysicalUser contactUser = new PhysicalUserController(getSession()).read(scanner, true);

        JuridicalUser juridicalUser = new JuridicalUser(getSession());
        juridicalUser.setUsername(username);
        juridicalUser.setPassword(password);
        juridicalUser.setName(name);
        juridicalUser.setAddress(address);
        juridicalUser.setContactUser(contactUser);
        return juridicalUser;
    }



    @Override
    public JuridicalUser read(Scanner scanner, boolean create) {
        return create ? Selector.readViaOidOrCreate(this, scanner) : Selector.readViaOid(this, scanner);
    }

    @Override
    public JuridicalUser read(Scanner scanner) {
        return read(scanner, false);
    }

    @Override
    public List<JuridicalUser> readAll() {
        return getSession().get(JuridicalUser.class);
    }

    @Override
    public JuridicalUser update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    private static final String OBJECT_NAME = "Juridical User";
    @Override
    protected String getManagedObjectName() {
        return OBJECT_NAME;
    }
}
