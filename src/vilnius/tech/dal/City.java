package vilnius.tech.dal;

import vilnius.tech.session.Session;

import java.io.Serializable;

public class City extends BaseOid implements Serializable {

    public City(Session session) {
        super(session, City.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    private String name;
    private Country country;
}
