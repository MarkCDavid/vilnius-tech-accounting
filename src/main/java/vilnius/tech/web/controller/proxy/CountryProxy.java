package vilnius.tech.web.controller.proxy;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.Country;
import vilnius.tech.hibernate.service.CountryService;
import vilnius.tech.web.controller.utils.HibernateUtils;
import vilnius.tech.web.controller.utils.JsonResponseUtils;

public class CountryProxy extends AbstractProxy<Country, CountryService> {
    @Override
    public ResponseEntity<String> post(Country country) {
        if(!validPost(country))
            return JsonResponseUtils.BAD("Data invalid to create the object.");

        return JsonResponseUtils.OK(createService().create(
                country.getName(),
                country.getCode()
        ));
    }

    @Override
    public ResponseEntity<String> put(Country country) {
        if(!validPut(country))
            return JsonResponseUtils.BAD("Data invalid to update the object.");

        return JsonResponseUtils.OK(createService().create(
                country.getName(),
                country.getCode()
        ));
    }

    private boolean validPost(Country country) {
        var name = country.getName();
        var code = country.getCode();
        return name != null && !name.isBlank() && code != null && !code.isBlank();
    }

    private boolean validPut(Country country) {
        var name = country.getName();
        var code = country.getCode();
        return country.getId() != null &&  name != null && !name.isBlank() && code != null && !code.isBlank();
    }

    @Override
    public CountryService createService() {
        return new CountryService(HibernateUtils.getSession());
    }
}
