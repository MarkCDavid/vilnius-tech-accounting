package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Scanner;

public class JuridicalUserController extends CRUDManager<JuridicalUser> {

    public JuridicalUserController(Company source, int indentation) {
        this.source = source;
        this.indentation = indentation;
    }


    @Override
    public JuridicalUser create(Scanner scanner) {
//        String username = UserInput.getString(scanner, "\t".repeat(indentation) + "Username");
//        String password = UserInput.getString(scanner, "\t".repeat(indentation) + "Password");
//        String name = UserInput.getString(scanner, "\t".repeat(indentation) + "Name");
//
//        System.out.println("\t".repeat(indentation) + "Address:");
//        Address address = new AddressController(source,indentation + 1).read(scanner, true);
//
//        System.out.println("\t".repeat(indentation) + "Contact:");
//        PhysicalUser contactUser = new PhysicalUserController(source,indentation + 1).read(scanner, true);
//
//        JuridicalUser juridicalUser = new JuridicalUser();
//        juridicalUser.setOid(source.getJuridicalUsers().size());
//        source.getJuridicalUsers().add(juridicalUser);
//
//        juridicalUser.setUsername(username);
//        juridicalUser.setPassword(password);
//        juridicalUser.setName(name);
//        juridicalUser.setAddress(address);
//        juridicalUser.setContactUser(contactUser);
//        return juridicalUser;
        return null;
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
        return source.getJuridicalUsers();
    }

    @Override
    public JuridicalUser update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    private final Company source;
    private final int indentation;

    private static final String OBJECT_NAME = "Juridical User";
    @Override
    protected String getManagedObjectName() {
        return OBJECT_NAME;
    }
}
