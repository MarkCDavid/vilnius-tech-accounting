package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.Country;
import vilnius.tech.hibernate.service.CountryService;
import vilnius.tech.web.controller.utils.HibernateUtils;
import vilnius.tech.web.controller.utils.JsonResponseUtils;
import vilnius.tech.web.controller.utils.Messages;

public class CountryProxy extends AbstractControllerProxy<Country, CountryService> {
    @Override
    public ResponseEntity<String> post(Country country) {
        if(post_Invalid(country))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        return JsonResponseUtils.OK(createService().create(
                country.getName(),
                country.getCode()
        ));
    }

    @Override
    public ResponseEntity<String> put(Country country) {
        if(put_Invalid(country))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var service = createService();

        var databaseCountry = service.find(country.getId());
        if(databaseCountry == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), country.getId()));

        databaseCountry.setName(country.getName());
        databaseCountry.setCode(country.getCode());

        return JsonResponseUtils.OK(service.update(databaseCountry));
    }

    private boolean post_Invalid(Country country) {
        return country.getName() == null || country.getName().isBlank() ||
                country.getCode() == null || country.getCode().isBlank();
    }

    private boolean put_Invalid(Country country) {
        return country.getId() == null ||
                country.getName() == null || country.getName().isBlank() ||
                country.getCode() == null ||  country.getCode().isBlank();
    }

    @Override
    protected CountryService createService() {
        return new CountryService(HibernateUtils.getSession());
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    public final static String ENTITY_NAME = "Country";
}
