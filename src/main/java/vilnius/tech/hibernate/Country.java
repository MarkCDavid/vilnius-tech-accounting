package vilnius.tech.hibernate;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

public class Country implements BaseEntity  {

    @Expose
    private Integer id;

    @Expose
    private String name;

    @Expose
    private String code;

    private Set<City> cities = new HashSet<>();

    public Country() {
    }

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return name;
    }
}