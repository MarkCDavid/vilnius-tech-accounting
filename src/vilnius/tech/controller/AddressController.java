package vilnius.tech.controller;

import vilnius.tech.dal.Address;
import vilnius.tech.dal.City;
import vilnius.tech.dal.Company;
import vilnius.tech.dal.Session;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Scanner;

public class AddressController implements CRUD<Address> {

    public AddressController(Session session, int indentation) {
        this.session = session;
        this.indentation = indentation;
    }

    @Override
    public Address create(Scanner scanner) {

        System.out.println("\t".repeat(indentation) + "City:");
        City city = new CityController(session, indentation + 1).read(scanner, true);

        String street = UserInput.getString(scanner, "\t".repeat(indentation) + "Street");
        String postal = UserInput.getString(scanner, "\t".repeat(indentation) + "Postal");

        Address address = new Address(session);
        address.setCity(city);
        address.setStreet(street);
        address.setPostal(postal);
        return address;
    }

    @Override
    public Address read(Scanner scanner, boolean create) {
        return create ? Selector.readViaOidOrCreate(this, scanner) : Selector.readViaOid(this, scanner);
    }

    @Override
    public Address read(Scanner scanner) {
        return read(scanner, false);
    }

    @Override
    public List<Address> readAll() {
        return session.get(Address.class);
    }

    @Override
    public Address update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    private final Session session;
    private final int indentation;
}
