package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.City;
import vilnius.tech.hibernate.service.AddressService;
import vilnius.tech.hibernate.service.CityService;
import vilnius.tech.web.utils.HibernateUtils;
import vilnius.tech.web.utils.JsonResponseUtils;
import vilnius.tech.web.utils.Messages;

public class AddressProxy extends AbstractControllerProxy<Address, AddressService> {
    @Override
    public ResponseEntity<String> post(Address address) {
        if(post_Invalid(address))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var city = getCity(address);
        if(city == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound_Field(getEntityName(), "name", address.getCity().getName()));

        return JsonResponseUtils.OK(createService().create(
                city,
                address.getStreet(),
                address.getPostal()
        ));
    }

    @Override
    public ResponseEntity<String> put(Address address) {
        if(put_Invalid(address))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var service = createService();

        var databaseAddress = service.find(address.getId());
        if(databaseAddress == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), address.getId()));

        if(cityPresent(address)) {
            var city = getCity(address);
            if(city == null)
                return JsonResponseUtils.BAD(Messages.itemNotFound_Field(getEntityName(), "name", address.getCity().getName()));
            databaseAddress.setCity(city);
        }

        if(addressStreetPresent(address)) {
            databaseAddress.setStreet(address.getStreet());
        }

        if(addressPostalPresent(address)) {
            databaseAddress.setPostal(address.getPostal());
        }

        return JsonResponseUtils.OK(service.update(databaseAddress));
    }

    private boolean post_Invalid(Address address) {
        return !addressStreetPresent(address) ||
               !addressPostalPresent(address) ||
               !cityPresent(address);
    }

    private boolean put_Invalid(Address address) {
        if(address.getId() == null)
            return true;

        return !addressStreetPresent(address) &&
               !addressPostalPresent(address) &&
               !cityPresent(address);
    }

    private boolean cityPresent(Address address) {
        return address.getCity() != null && address.getCity().getName() != null && !address.getCity().getName().isBlank();
    }

    private boolean addressStreetPresent(Address address) {
        return address.getStreet() != null && !address.getStreet().isBlank();
    }

    private boolean addressPostalPresent(Address address) {
        return address.getPostal() != null && !address.getPostal().isBlank();
    }


    private City getCity(Address address) {
        var cityService = new CityService(HibernateUtils.getSession());
        var cities = cityService.find_Name(address.getCity().getName());
        return cities.size() == 1 ? cities.get(0) : null;
    }

    @Override
    protected AddressService createService() {
        return new AddressService(HibernateUtils.getSession());
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    public final static String ENTITY_NAME = "Address";
}
