package vilnius.tech.controller;

import vilnius.tech.dal.City;
import vilnius.tech.dal.Country;
import vilnius.tech.dal.Session;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Scanner;

public class CityController extends CRUDManager<City> implements CRUD<City> {

    public CityController(Session session, int indentation) {
        this.session = session;
        this.indentation = indentation;
    }

    @Override
    public City create(Scanner scanner) {
        String name = UserInput.getString(scanner, "\t".repeat(indentation) + "City name");

        System.out.println("\t".repeat(indentation) + "Country:");
        Country country = new CountryController(session, indentation + 1).read(scanner, true);

        City city = new City(session);
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
        return session.get(City.class);
    }

    @Override
    public City update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    private final Session session;
    private final int indentation;

    @Override
    protected String getManagedObjectName() {
        return "City";
    }
}
