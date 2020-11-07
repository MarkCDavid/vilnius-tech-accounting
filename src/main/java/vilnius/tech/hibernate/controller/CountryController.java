package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.Country;
import vilnius.tech.session.HibernateController;

public class CountryController extends HibernateController<Country> {

    public CountryController(Session session) {
        super(Country.class, session);
    }

    public Country create(String name, String code) {
        var country = new Country();
        country.setName(name);
        country.setCode(code);
        return update(country);
    }

}
