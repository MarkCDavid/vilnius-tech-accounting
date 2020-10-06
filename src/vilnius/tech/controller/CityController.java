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

    public CityController(Session session) {
        super(session);
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
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    @Override
    protected String getManagedObjectName() {
        return "City";
    }
}
