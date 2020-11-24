package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.JuridicalUser;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.hibernate.service.AddressService;
import vilnius.tech.hibernate.service.JuridicalUserService;
import vilnius.tech.hibernate.service.PhysicalUserService;
import vilnius.tech.web.utils.HibernateUtils;
import vilnius.tech.web.utils.JsonResponseUtils;
import vilnius.tech.web.utils.Messages;

public class JuridicalUserProxy extends AbstractControllerProxy<JuridicalUser, JuridicalUserService> {
    @Override
    public ResponseEntity<String> post(JuridicalUser juridicalUser) {
        if(post_Invalid(juridicalUser))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var contactUser = getContactUser(juridicalUser);
        var address = getAddress(juridicalUser);

        return JsonResponseUtils.OK(createService().create(
                juridicalUser.getUsername(),
                juridicalUser.getPassword(),
                juridicalUser.getName(),
                address,
                contactUser
        ));
    }

    @Override
    public ResponseEntity<String> put(JuridicalUser juridicalUser) {
        if(put_Invalid(juridicalUser))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var service = createService();

        var databaseJuridicalUser = service.find(juridicalUser.getId());
        if(databaseJuridicalUser == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), juridicalUser.getId()));

        if(addressPresent(juridicalUser)) {
            var address = getAddress(juridicalUser);
            if(address == null)
                return JsonResponseUtils.BAD(Messages.itemNotFound(AddressProxy.ENTITY_NAME, juridicalUser.getAddress().getId()));
            databaseJuridicalUser.setAddress(address);
        }

        if(contactUserPresent(juridicalUser)) {
            var contactUser = getContactUser(juridicalUser);
            if(contactUser == null)
                return JsonResponseUtils.BAD(Messages.itemNotFound_Field(AddressProxy.ENTITY_NAME, "username", juridicalUser.getContactUser().getUsername()));
            databaseJuridicalUser.setContactUser(contactUser);
        }

        if(namePresent(juridicalUser)) {
            databaseJuridicalUser.setName(juridicalUser.getName());
        }

        return JsonResponseUtils.OK(service.update(databaseJuridicalUser));
    }

    private boolean post_Invalid(JuridicalUser juridicalUser) {
        return !usernamePresent(juridicalUser) ||
               !passwordPresent(juridicalUser);
    }

    private boolean put_Invalid(JuridicalUser juridicalUser) {
        if(juridicalUser.getId() == null)
            return true;

        return !namePresent(juridicalUser) &&
               !addressPresent(juridicalUser) &&
               !contactUserPresent(juridicalUser);
    }

    private boolean contactUserPresent(JuridicalUser juridicalUser) {
        return juridicalUser.getContactUser() != null &&
                juridicalUser.getContactUser().getUsername() != null &&
                !juridicalUser.getContactUser().getUsername().isBlank();
    }


    private boolean addressPresent(JuridicalUser juridicalUser) {
        return juridicalUser.getAddress() != null &&
                juridicalUser.getAddress().getId() != null;
    }

    private boolean usernamePresent(JuridicalUser juridicalUser) {
        return juridicalUser.getUsername() != null && !juridicalUser.getUsername().isBlank();
    }

    private boolean passwordPresent(JuridicalUser juridicalUser) {
        return juridicalUser.getPassword() != null && !juridicalUser.getPassword().isBlank();
    }

    private boolean namePresent(JuridicalUser juridicalUser) {
        return juridicalUser.getName() != null && !juridicalUser.getName().isBlank();
    }

    private PhysicalUser getContactUser(JuridicalUser juridicalUser) {
        var physicalUserService = new PhysicalUserService(HibernateUtils.getSession());

        if(!contactUserPresent(juridicalUser))
            return null;

        return physicalUserService.find_Username(juridicalUser.getContactUser().getUsername());
    }


    private Address getAddress(JuridicalUser juridicalUser) {
        var addressService = new AddressService(HibernateUtils.getSession());

        if(!addressPresent(juridicalUser))
            return null;

        return addressService.find(juridicalUser.getAddress().getId());
    }

    @Override
    protected JuridicalUserService createService() {
        return new JuridicalUserService(HibernateUtils.getSession());
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    public final static String ENTITY_NAME = "Juridical User";
}
