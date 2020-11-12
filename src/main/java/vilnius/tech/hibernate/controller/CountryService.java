package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.Country;

public class CountryService extends HibernateService<Country> {

    public CountryService(Session session) {
        super(Country.class, session);
    }

    public Country create(String name, String code) {
        var country = new Country();
        country.setName(name);
        country.setCode(code);
        return update(country);
    }

}
