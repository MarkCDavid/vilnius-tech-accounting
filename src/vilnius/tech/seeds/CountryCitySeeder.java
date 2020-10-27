package vilnius.tech.seeds;

import vilnius.tech.dal.City;
import vilnius.tech.dal.Country;
import vilnius.tech.session.Session;

public class CountryCitySeeder implements Seeder {


    @Override
    public void seed(Session session) {
        seedLithuania(session);
        seedLatvia(session);
    }

    private void seedLatvia(Session session) {
        Country latvia = createCountry(session, "Latvia", "LV");
        createCity(session, "Daugavpils", latvia);
        createCity(session, "Jēkabpils", latvia);
        createCity(session, "Jelgava", latvia);
        createCity(session, "Jūrmala", latvia);
        createCity(session, "Liepāja", latvia);
        createCity(session, "Rēzekne", latvia);
        createCity(session, "Rīga", latvia);
        createCity(session, "Valmiera", latvia);
        createCity(session, "Ventspils", latvia);

    }

    private void seedLithuania(Session session) {
        Country lithuania = createCountry(session, "Lithuania", "LT");
        createCity(session, "Vilnius", lithuania);
        createCity(session, "Kaunas", lithuania);
        createCity(session, "Klaipėda", lithuania);
        createCity(session, "Šiauliai", lithuania);
        createCity(session, "Panevėžys", lithuania);
        createCity(session, "Alytus", lithuania);
        createCity(session, "Marijampolė", lithuania);
        createCity(session, "Mažeikiai", lithuania);
        createCity(session, "Jonava", lithuania);
        createCity(session, "Utena", lithuania);
        createCity(session, "Kėdainiai", lithuania);
        createCity(session, "Tauragė", lithuania);
        createCity(session, "Telšiai", lithuania);
        createCity(session, "Ukmergė", lithuania);

    }

    private Country createCountry(Session session, String name, String code) {
        Country country = new Country(session);
        country.setName(name);
        country.setCode(code);
        return country;
    }

    private City createCity(Session session, String name, Country country) {
        City city = new City(session);
        city.setName(name);
        city.setCountry(country);
        return city;
    }
}
