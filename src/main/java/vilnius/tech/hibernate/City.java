package vilnius.tech.hibernate;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

public class City implements BaseEntity  {

    @Expose
    private Integer id;

    @Expose
    private String name;

    @Expose
    private Country country;

    private Set<Address> addresses = new HashSet<>();

    public City() {
    }

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return name;
    }
}
