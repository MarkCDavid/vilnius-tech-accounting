package vilnius.tech.controller;

import vilnius.tech.dal.Address;
import vilnius.tech.dal.City;
import vilnius.tech.dal.Company;
import vilnius.tech.dal.ContactInformation;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Scanner;

public class ContactInformationController implements CRUD<ContactInformation> {

    public ContactInformationController(Company source, int indentation) {
        this.source = source;
        this.indentation = indentation;
    }

    @Override
    public ContactInformation create(Scanner scanner) {
//
//        System.out.println("\t".repeat(indentation) + "Address:");
//        Address address = new AddressController(source,indentation + 1).read(scanner, true);
//
//        String email = UserInput.getString(scanner, "\t".repeat(indentation) + "Email");
//        String phoneNumber = UserInput.getString(scanner, "\t".repeat(indentation) + "Phone Number");
//
//        ContactInformation contactInformation = new ContactInformation();
//        contactInformation.setOid(source.getContactsInformation().size());
//        source.getContactsInformation().add(contactInformation);
//
//        contactInformation.setAddress(address);
//        contactInformation.setEmail(email);
//        contactInformation.setPhoneNumber(phoneNumber);
//        return contactInformation;
        return null;
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
        return source.getContactsInformation();
    }

    @Override
    public ContactInformation update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    private final Company source;

    private final int indentation;
}
