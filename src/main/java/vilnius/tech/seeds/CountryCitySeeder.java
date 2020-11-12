package vilnius.tech.seeds;

import org.hibernate.Session;
import vilnius.tech.hibernate.City;
import vilnius.tech.hibernate.Country;
import vilnius.tech.hibernate.service.CityService;
import vilnius.tech.hibernate.service.CountryService;

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
        var controller = new CountryService(session);
        return controller.create(name, code);
    }

    private City createCity(Session session, String name, Country country) {
        var controller = new CityService(session);
        return controller.create(name, country);
    }
}
