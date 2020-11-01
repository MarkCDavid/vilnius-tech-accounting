package vilnius.tech.dal;

import vilnius.tech.session.Session;

import java.io.Serializable;

public class Address extends BaseOid implements Serializable {

    public Address(Session session) {
        super(session, Address.class);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    private City city;
    private String street;
    private String postal;
}
