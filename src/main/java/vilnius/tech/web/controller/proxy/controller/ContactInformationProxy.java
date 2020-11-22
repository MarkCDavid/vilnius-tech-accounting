package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.ContactInformation;
import vilnius.tech.hibernate.service.AddressService;
import vilnius.tech.hibernate.service.ContactInformationService;
import vilnius.tech.web.controller.utils.HibernateUtils;
import vilnius.tech.web.controller.utils.JsonResponseUtils;
import vilnius.tech.web.controller.utils.Messages;

public class ContactInformationProxy extends AbstractControllerProxy<ContactInformation, ContactInformationService> {
    @Override
    public ResponseEntity<String> post(ContactInformation contactInformation) {
        if(post_Invalid(contactInformation))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var address = getAddress(contactInformation);
        if(address == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(AddressProxy.ENTITY_NAME, contactInformation.getAddress().getId()));

        return JsonResponseUtils.OK(createService().create(
                address,
                contactInformation.getEmail(),
                contactInformation.getPhoneNumber()
        ));
    }

    @Override
    public ResponseEntity<String> put(ContactInformation contactInformation) {
        if(put_Invalid(contactInformation))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var service = createService();

        var databaseContactInformation = service.find(contactInformation.getId());
        if(databaseContactInformation == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), contactInformation.getId()));

        if(addressPresent(contactInformation)) {
            var address = getAddress(contactInformation);
            if(address == null)
                return JsonResponseUtils.BAD(Messages.itemNotFound(AddressProxy.ENTITY_NAME, contactInformation.getAddress().getId()));
            databaseContactInformation.setAddress(address);
        }

        if(contactInformationEmailPresent(contactInformation)) {
            databaseContactInformation.setEmail(contactInformation.getEmail());
        }

        if(contactInformationPhonePresent(contactInformation)) {
            databaseContactInformation.setPhoneNumber(contactInformation.getPhoneNumber());
        }

        return JsonResponseUtils.OK(service.update(databaseContactInformation));
    }

    private boolean post_Invalid(ContactInformation contactInformation) {
        return !contactInformationEmailPresent(contactInformation) ||
               !contactInformationPhonePresent(contactInformation) ||
               !addressPresent(contactInformation);
    }

    private boolean put_Invalid(ContactInformation contactInformation) {
        if(contactInformation.getId() == null)
            return true;

        return !contactInformationEmailPresent(contactInformation) &&
               !contactInformationPhonePresent(contactInformation) &&
               !addressPresent(contactInformation);
    }

    private boolean addressPresent(ContactInformation contactInformation) {
        return contactInformation.getAddress() != null &&
                contactInformation.getAddress().getId() != null;
    }

    private boolean contactInformationEmailPresent(ContactInformation contactInformation) {
        return contactInformation.getEmail() != null && !contactInformation.getEmail() .isBlank();
    }

    private boolean contactInformationPhonePresent(ContactInformation contactInformation) {
        return contactInformation.getPhoneNumber() != null && !contactInformation.getPhoneNumber().isBlank();
    }

    private Address getAddress(ContactInformation contactInformation) {
        var addressService = new AddressService(HibernateUtils.getSession());
        return addressService.find(contactInformation.getAddress().getId());
    }

    @Override
    protected ContactInformationService createService() {
        return new ContactInformationService(HibernateUtils.getSession());
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    public final static String ENTITY_NAME = "Contact Information";
}
