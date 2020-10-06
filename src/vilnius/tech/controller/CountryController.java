package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class CountryController extends CRUDManager<Country> implements CRUD<Country> {

    public CountryController(Session session) {
        super(session);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected void initializeReferenceMap(Map<String, Reference> referenceMap) {
        referenceMap.put("cities", new Reference(City.class, (Function<City, Integer>) city -> city.getCountry().getOid()));
    }

    @Override
    public Country create(Scanner scanner) {
        String name = UserInput.getString(scanner, "Country name");
        String code = UserInput.getString(scanner, "Country code");

        Country country = new Country(getSession());

        country.setName(name);
        country.setCode(code);
        return country;
    }

    @Override
    public List<Country> readAll() {
        return getSession().get(Country.class);
    }

    @Override
    public Country update(Scanner scanner) {
        Country country = read(scanner);

        if(UserInput.getUserConfirmation(scanner, "Update Country name")) {
            country.setName(UserInput.getString(scanner, "Country name"));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Country code")) {
            country.setCode(UserInput.getString(scanner, "Country code"));
        }

        return country;
    }

    @Override
    public void delete(Scanner scanner) {
        Country country = read(scanner);
        if(country == null || country.isDeleted())
            return;

        List<City> cRefs = getSession().query(City.class, c -> c.getCountry().getOid() == country.getOid());
        if(!cRefs.isEmpty() && !UserInput.getDeleteConfirmation(scanner, getManagedObjectName(), "City", cRefs.size())) {
            return;
        }

        for(City c: cRefs) {
            c.setCountry(null);
        }

        country.setDeleted(true);
    }


    @Override
    protected String getManagedObjectName() {
        return "Country";
    }
}
