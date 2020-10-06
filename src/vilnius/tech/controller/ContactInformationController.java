package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Scanner;

public class ContactInformationController extends CRUDManager<ContactInformation> implements CRUD<ContactInformation> {

    public ContactInformationController(Session session) {
        super(session);
    }

    @Override
    public ContactInformation create(Scanner scanner) {

        System.out.println("Address:");
        Address address = new AddressController(getSession()).read(scanner, true);

        String email = UserInput.getString(scanner, "Email");
        String phoneNumber = UserInput.getString(scanner, "Phone number");

        ContactInformation contactInformation = new ContactInformation(getSession());
        contactInformation.setAddress(address);
        contactInformation.setEmail(email);
        contactInformation.setPhoneNumber(phoneNumber);
        return contactInformation;
    }
    @Override
    public List<ContactInformation> readAll() {
        return getSession().get(ContactInformation.class);
    }

    @Override
    public ContactInformation update(Scanner scanner) {
        ContactInformation contactInformation = read(scanner);

        if(UserInput.getUserConfirmation(scanner, "Update Address")) {
            contactInformation.setAddress(new AddressController(getSession()).read(scanner, true));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Email")) {
            contactInformation.setEmail(UserInput.getString(scanner, "Email"));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Phone Number")) {
            contactInformation.setPhoneNumber(UserInput.getString(scanner, "Phone number"));
        }

        return contactInformation;
    }

    @Override
    public void delete(Scanner scanner) {
        ContactInformation contactInformation = read(scanner);
        if(contactInformation == null || contactInformation.isDeleted())
            return;

        List<PhysicalUser> puRefs = getSession().query(PhysicalUser.class, pu -> pu.getContactInfo().getOid() == contactInformation.getOid());
        if(!puRefs.isEmpty() && !UserInput.getDeleteConfirmation(scanner, getManagedObjectName(), "Physical User", puRefs.size())) {
            return;
        }

        for(PhysicalUser pu: puRefs) {
            pu.setContactInformation(null);
        }

        contactInformation.setDeleted(true);
    }

    @Override
    protected String getManagedObjectName() {
        return "Contact Information";
    }
}
