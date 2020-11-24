package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.City;
import vilnius.tech.hibernate.Country;
import vilnius.tech.hibernate.service.CityService;
import vilnius.tech.hibernate.service.CountryService;
import vilnius.tech.web.utils.HibernateUtils;
import vilnius.tech.web.utils.JsonResponseUtils;
import vilnius.tech.web.utils.Messages;

public class CityProxy extends AbstractControllerProxy<City, CityService> {
    @Override
    public ResponseEntity<String> post(City city) {
        if(post_Invalid(city))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var country = getCountry(city);
        if(country == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound_Field(getEntityName(), "code", city.getCountry().getCode()));

        return JsonResponseUtils.OK(createService().create(
                city.getName(),
                country
        ));
    }

    @Override
    public ResponseEntity<String> put(City city) {
        if(put_Invalid(city))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var service = createService();

        var databaseCity = service.find(city.getId());
        if(databaseCity == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), city.getId()));

        if(cityNamePresent(city)) {
            databaseCity.setName(city.getName());
        }

        if(countryPresent(city)) {
            var country = getCountry(city);
            if(country == null)
                return JsonResponseUtils.BAD(Messages.itemNotFound_Field(getEntityName(), "code", city.getCountry().getCode()));

            databaseCity.setCountry(country);
        }

        return JsonResponseUtils.OK(service.update(databaseCity));
    }

    private boolean post_Invalid(City city) {
        return !cityNamePresent(city) || !countryPresent(city);
    }

    private boolean put_Invalid(City city) {

        if(city.getId() == null)
            return true;

        return !cityNamePresent(city) && !countryPresent(city);
    }

    private boolean countryPresent(City city) {
        return city.getCountry() != null && city.getCountry().getCode() != null && !city.getCountry().getCode().isBlank();
    }

    private boolean cityNamePresent(City city) {
        return city.getName() != null && !city.getName().isBlank();
    }

    private Country getCountry(City city) {
        var countryService = new CountryService(HibernateUtils.getSession());
        var countries = countryService.find_Code(city.getCountry().getCode());
        return countries.size() == 1 ? countries.get(0) : null;
    }

    @Override
    protected CityService createService() {
        return new CityService(HibernateUtils.getSession());
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    public final static String ENTITY_NAME = "City";
}
