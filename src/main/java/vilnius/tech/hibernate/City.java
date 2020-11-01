package vilnius.tech.hibernate;

import java.util.ArrayList;
import java.util.List;

public class City {

    private Integer id;

    private String name;

    private Country country;
    private List<Address> addresses = new ArrayList<>();

    public City() {
    }

    public City(Integer id, String name, Country country, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.addresses = addresses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
