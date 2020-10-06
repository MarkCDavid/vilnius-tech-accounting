package vilnius.tech.dal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Country extends BaseOid implements Serializable {

    public Country(Session session) {
        super(session);
        cities = new ArrayList<>();
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

    @Override
    public String toString() {
        return String.format("%s (%s)", name, code);
    }

    @Override
    public String toShortString() {
        return toString();
    }





    private String name;
    private String code;

    public List<City> getCities() {
        return cities;
    }

    private final List<City> cities;
}
