package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class CityController extends CRUDManager<City> implements CRUD<City> {

    public CityController(Session session) {
        super(session);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected void initializeReferenceMap(Map<String, Reference> referenceMap) {
        referenceMap.put("addresses", new Reference(Address.class, (Function<Address, Integer>) address -> address.getCity().getOid()));
    }

    @Override
    public City create(Scanner scanner) {
        String name = UserInput.getString(scanner, "City name");

        System.out.println("Country:");
        Country country = new CountryController(getSession()).read(scanner, true);

        City city = new City(getSession());
        city.setName(name);
        city.setCountry(country);
        return city;
    }

    @Override
    public List<City> readAll() {
        return getSession().get(City.class);
    }

    @Override
    public City update(Scanner scanner) {
        City city = read(scanner);

        if(UserInput.getUserConfirmation(scanner, "Update Country")) {
            city.setCountry(new CountryController(getSession()).read(scanner, true));
        }

        if(UserInput.getUserConfirmation(scanner, "Update City name")) {
            city.setName(UserInput.getString(scanner, "City name"));
        }

        return city;
    }

    @Override
    public void delete(Scanner scanner) {
        City city = read(scanner);
        if(city == null || city.isDeleted())
            return;

        List<Address> aRefs = getSession().query(Address.class, a -> a.getCity().getOid() == city.getOid());
        if(!aRefs.isEmpty() && !UserInput.getDeleteConfirmation(scanner, getManagedObjectName(), "Address", aRefs.size())) {
            return;
        }

        for(Address a: aRefs) {
            a.setCity(null);
        }

        city.setDeleted(true);
    }

    @Override
    protected String getManagedObjectName() {
        return "City";
    }
}
