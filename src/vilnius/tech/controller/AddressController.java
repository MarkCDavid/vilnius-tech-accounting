package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Scanner;

public class AddressController extends CRUDManager<Address> implements CRUD<Address> {

    public AddressController(Session session) {
        super(session);
    }

    @Override
    public Address create(Scanner scanner) {

        System.out.println("City:");
        City city = new CityController(getSession()).read(scanner, true);

        String street = UserInput.getString(scanner, "Street");
        String postal = UserInput.getString(scanner, "Postal");

        Address address = new Address(getSession());
        address.setCity(city);
        address.setStreet(street);
        address.setPostal(postal);
        return address;
    }

    @Override
    public List<Address> readAll() {
        return getSession().get(Address.class);
    }

    @Override
    public Address update(Scanner scanner) {
        Address address = read(scanner);

        if(UserInput.getUserConfirmation(scanner, "Update City")) {
            address.setCity(new CityController(getSession()).read(scanner, true));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Street")) {
            address.setStreet(UserInput.getString(scanner, "Street"));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Postal")) {
            address.setPostal(UserInput.getString(scanner, "Postal"));
        }

        return address;
    }

    @Override
    public void delete(Scanner scanner) {
        Address address = read(scanner);
        if(address == null || address.isDeleted())
            return;

        List<ContactInformation> ciRefs = getSession().query(ContactInformation.class, ci -> ci.getAddress().getOid() == address.getOid());
        if(!ciRefs.isEmpty() && !UserInput.getDeleteConfirmation(scanner, getManagedObjectName(), "ContactInformation", ciRefs.size())) {
            return;
        }

        List<JuridicalUser> juRefs = getSession().query(JuridicalUser.class, ju -> ju.getAddress().getOid() == address.getOid());
        if(!juRefs.isEmpty() && !UserInput.getDeleteConfirmation(scanner, getManagedObjectName(), "Juridical User", ciRefs.size())) {
            return;
        }

        for(ContactInformation ci: ciRefs) {
            ci.setAddress(null);
        }

        for(JuridicalUser ju: juRefs) {
            ju.setAddress(null);
        }

        address.setDeleted(true);
    }

    @Override
    protected String getManagedObjectName() {
        return "Address";
    }
}
