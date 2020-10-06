package vilnius.tech.controller;

import vilnius.tech.dal.City;
import vilnius.tech.dal.Country;
import vilnius.tech.dal.Session;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class CountryController extends CRUDManager<Country> implements CRUD<Country> {

    public CountryController(Session session, int indentation) {
        super(session);
        this.indentation = indentation;
    }

    @Override
    protected void initializeReferenceMap(Map<String, Reference> referenceMap) {
        referenceMap.put("cities", new Reference(City.class, (Function<City, Integer>) city -> city.getCountry().getOid()));
    }

    @Override
    public Country create(Scanner scanner) {
        String name = UserInput.getString(scanner, "\t".repeat(indentation) + "Country name");
        String code = UserInput.getString(scanner, "\t".repeat(indentation) + "Country code");

        Country country = new Country(getSession());

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
        return getSession().get(Country.class);
    }

    @Override
    public Country update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    private final int indentation;

    @Override
    protected String getManagedObjectName() {
        return "COUNTNRTNRN";
    }
}
