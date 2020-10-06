package vilnius.tech.dal;

import java.io.Serializable;

public class Address extends BaseOid implements Serializable {

    public Address(Session session) {
        super(session);
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

    @Override
    public String toString() {
        if(isDeleted()) return "<deleted>";
        return formatReference(city, "City") +
                formatValue(street, "Street") +
                formatValue(postal, "Postal Code");
    }

    @Override
    public String toShortString() {
        if(isDeleted()) return "<deleted>";
        return String.format("%s, %s, %s", city, street, postal);
    }
}
