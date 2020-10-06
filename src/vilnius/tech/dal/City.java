package vilnius.tech.dal;

import java.io.Serializable;

public class City extends BaseOid implements Serializable {

    public City(Session session) {
        super(session);
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

    @Override
    public String toString() {
        return formatValue(name, "City Name") +
                formatReference(country, "Country");
    }

    @Override
    public String toShortString() {
        return String.format("%s - %s", name, country.toShortString());
    }
}
