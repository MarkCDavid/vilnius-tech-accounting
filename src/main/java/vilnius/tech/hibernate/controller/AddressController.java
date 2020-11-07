package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.City;
import vilnius.tech.session.HibernateController;

public class AddressController extends HibernateController<Address> {

    public AddressController(Session session) {
        super(Address.class, session);
    }

    public Address create(City city, String street, String postal) {
        var address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setPostal(postal);
        return update(address);
    }

}
