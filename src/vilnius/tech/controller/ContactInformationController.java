package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Scanner;

public class ContactInformationController implements CRUD<ContactInformation> {

    public ContactInformationController(Session session) {
        this.session = session;
    }

    @Override
    public ContactInformation create(Scanner scanner) {

        System.out.println("Address:");
        Address address = new AddressController(session).read(scanner, true);

        String email = UserInput.getString(scanner, "Email");
        String phoneNumber = UserInput.getString(scanner, "Phone Number");

        ContactInformation contactInformation = new ContactInformation(session);

        contactInformation.setAddress(address);
        contactInformation.setEmail(email);
        contactInformation.setPhoneNumber(phoneNumber);
        return contactInformation;
    }

    @Override
    public ContactInformation read(Scanner scanner, boolean create) {
        return create ? Selector.readViaOidOrCreate(this, scanner) : Selector.readViaOid(this, scanner);
    }

    @Override
    public ContactInformation read(Scanner scanner) {
        return read(scanner, false);
    }

    @Override
    public List<ContactInformation> readAll() {
        return session.get(ContactInformation.class);
    }

    @Override
    public ContactInformation update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    private final Session session;
}
