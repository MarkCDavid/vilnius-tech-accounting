package vilnius.tech.controller;

import vilnius.tech.dal.Company;
import vilnius.tech.dal.ContactInformation;
import vilnius.tech.dal.Country;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

public class CountryController implements CRUD<Country> {

    public CountryController(Company source, int indentation) {
        this.source = source;
        this.indentation = indentation;
    }

    @Override
    public Country create(Scanner scanner) {
        String name = UserInput.getString(scanner, "\t".repeat(indentation) + "Country name");
        String code = UserInput.getString(scanner, "\t".repeat(indentation) + "Country code");

        Country country = new Country();
        country.setOid(source.getCountries().size());
        source.getCountries().add(country);

        country.setName(name);
        country.setCode(code);
        return country;
    }

    @Override
    public Country read(Scanner scanner, boolean create) {
        return create ? Selector.readViaOidOrCreate(this, scanner) : Selector.readViaOid(this, scanner);
    }

    @Override
    public Country read(Scanner scanner) {
        return read(scanner, false);
    }

    @Override
    public List<Country> readAll() {
        return source.getCountries();
    }

    @Override
    public Country update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    private final Company source;
    private final int indentation;
}
