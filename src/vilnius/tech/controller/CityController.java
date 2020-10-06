package vilnius.tech.controller;

import vilnius.tech.dal.BaseOid;
import vilnius.tech.dal.City;
import vilnius.tech.dal.Country;
import vilnius.tech.dal.Session;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class CityController extends CRUDManager<City> implements CRUD<City> {

    public CityController(Session session, int indentation) {
        super(session);
        this.indentation = indentation;
    }

    @Override
    protected void initializeReferenceMap(Map<String, Reference> referenceMap) {
        referenceMap.put("countries", new Reference(Country.class, (Function<City, Integer>) city -> city.getCountry().getOid()));
    }

    @Override
    public City create(Scanner scanner) {
        String name = UserInput.getString(scanner, "\t".repeat(indentation) + "City name");

        System.out.println("\t".repeat(indentation) + "Country:");
        Country country = new CountryController(getSession(), indentation + 1).read(scanner, true);

        City city = new City(getSession());
        city.setName(name);
        city.setCountry(country);
        return city;
    }

    @Override
    public City read(Scanner scanner, boolean create) {
        return create ? Selector.readViaOidOrCreate(this, scanner) : Selector.readViaOid(this, scanner);
    }

    @Override
    public City read(Scanner scanner) {
        return read(scanner, false);
    }

    @Override
    public List<City> readAll() {
        return getSession().get(City.class);
    }

    @Override
    public City update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    private final int indentation;

    @Override
    protected String getManagedObjectName() {
        return "City";
    }
}
