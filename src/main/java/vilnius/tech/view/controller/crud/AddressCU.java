package vilnius.tech.view.controller.crud;

import vilnius.tech.dal.Address;
import vilnius.tech.dal.City;
import vilnius.tech.session.Session;

public class AddressCU {

    public static Address create(Session session, City city, String street, String postal) {
        var address = new Address(session);
        update(address, city, street, postal);
        return address;
    }

    public static void update(Address address, City city, String street, String postal) {
        address.setCity(city);
        address.setPostal(street);
        address.setStreet(postal);
    }

    private AddressCU() { }
}
