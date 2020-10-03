package vilnius.tech.controller;

import vilnius.tech.dal.Address;
import vilnius.tech.dal.City;
import vilnius.tech.dal.Company;
import vilnius.tech.dal.Country;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Scanner;

public class CityController implements CRUD<City> {

    public CityController(Company source, int indentation) {
        this.source = source;
        this.indentation = indentation;
    }

    @Override
    public City create(Scanner scanner) {
        String name = UserInput.getString(scanner, "\t".repeat(indentation) + "City name");

        System.out.println("\t".repeat(indentation) + "Country:");
        Country country = new CountryController(source, indentation + 1).read(scanner, true);

        City city = new City();
        city.setOid(source.getCities().size());
        source.getCities().add(city);

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
        return source.getCities();
    }

    @Override
    public City update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    private final Company source;
    private final int indentation;
}
