package vilnius.tech.controller;

import vilnius.tech.dal.City;
import vilnius.tech.dal.Country;
import vilnius.tech.dal.Session;
import vilnius.tech.utils.Display;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CountryController extends CRUDManager<Country> implements CRUD<Country> {

    public CountryController(Session session, int indentation) {
        this.session = session;
        this.indentation = indentation;
    }

    @Override
    public Country create(Scanner scanner) {
        String name = UserInput.getString(scanner, "\t".repeat(indentation) + "Country name");
        String code = UserInput.getString(scanner, "\t".repeat(indentation) + "Country code");

        Country country = new Country(session);

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
        return session.get(Country.class);
    }

    @Override
    public Country update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    @Override
    protected void showOptions(Scanner scanner) {
        super.showOptions(scanner);
        System.out.println("cities - show cities of selected country");
    }

    @Override
    protected void matchOptions(Scanner scanner, String userInput) {
        super.matchOptions(scanner, userInput);
        if(Objects.equals(userInput, "cities")) {
            Country country = read(scanner);
            for(City city: session.query(City.class, city -> city.getCountry().getOid() == country.getOid(), false)) {
                System.out.printf("%s) %s%n", city.getOid(), city);
            }
        }
    }

    private final Session session;
    private final int indentation;

    @Override
    protected String getManagedObjectName() {
        return "CONTRY";
    }
}
