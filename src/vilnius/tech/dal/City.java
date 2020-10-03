package vilnius.tech.dal;

import java.io.Serializable;

public class City extends BaseOid implements Serializable {

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

    @Override
    public String toString() {
        return String.format("%s - %s", name, country);
    }

    @Override
    public String toShortString() {
        return toString();
    }
}
