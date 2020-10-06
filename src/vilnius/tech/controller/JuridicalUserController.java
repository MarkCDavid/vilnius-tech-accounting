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
    public List<JuridicalUser> readAll() {
        return getSession().get(JuridicalUser.class);
    }

    @Override
    public JuridicalUser update(Scanner scanner) {
        JuridicalUser juridicalUser = read(scanner);

        if(UserInput.getUserConfirmation(scanner, "Update Username")) {
            juridicalUser.setName(UserInput.getString(scanner, "Username"));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Password")) {
            juridicalUser.setPassword(UserInput.getString(scanner, "Password"));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Name")) {
            juridicalUser.setName(UserInput.getString(scanner, "Name"));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Address")) {
            juridicalUser.setAddress(new AddressController(getSession()).read(scanner, true));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Contact")) {
            juridicalUser.setContactUser(new PhysicalUserController(getSession()).read(scanner, true));
        }

        return juridicalUser;
    }

    @Override
    public void delete(Scanner scanner) {
        JuridicalUser juridicalUser = read(scanner);
        if(juridicalUser == null || juridicalUser.isDeleted())
            return;


        juridicalUser.setDeleted(true);
    }

    @Override
    protected String getManagedObjectName() {
        return "Juridical User";
    }
}
