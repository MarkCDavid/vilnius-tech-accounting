package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.City;

import javax.persistence.criteria.Root;

public class AddressService extends HibernateService<Address> {

    public AddressService(Session session) {
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
