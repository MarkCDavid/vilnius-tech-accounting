package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.ContactInformation;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.hibernate.service.ContactInformationService;
import vilnius.tech.hibernate.service.PhysicalUserService;
import vilnius.tech.web.utils.HibernateUtils;
import vilnius.tech.web.utils.JsonResponseUtils;
import vilnius.tech.web.utils.Messages;

public class PhysicalUserProxy extends AbstractControllerProxy<PhysicalUser, PhysicalUserService> {
    @Override
    public ResponseEntity<String> post(PhysicalUser physicalUser) {
        if(post_Invalid(physicalUser))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var contactInformation = getContactInformation(physicalUser);

        return JsonResponseUtils.OK(createService().create(
                physicalUser.getUsername(),
                physicalUser.getPassword(),
                physicalUser.getName(),
                physicalUser.getSurname(),
                contactInformation
        ));
    }

    @Override
    public ResponseEntity<String> put(PhysicalUser physicalUser) {
        if(put_Invalid(physicalUser))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var service = createService();

        var databasePhysicalUser = service.find(physicalUser.getId());
        if(databasePhysicalUser == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), physicalUser.getId()));

        if(contactInformationPresent(physicalUser)) {
            var contactInformation = getContactInformation(physicalUser);
            if(contactInformation == null)
                return JsonResponseUtils.BAD(Messages.itemNotFound(AddressProxy.ENTITY_NAME, physicalUser.getContactInformation().getId()));
            databasePhysicalUser.setContactInformation(contactInformation);
        }

        if(namePresent(physicalUser)) {
            databasePhysicalUser.setName(physicalUser.getName());
        }

        if(surnamePresent(physicalUser)) {
            databasePhysicalUser.setSurname(physicalUser.getSurname());
        }

        return JsonResponseUtils.OK(service.update(databasePhysicalUser));
    }

    private boolean post_Invalid(PhysicalUser physicalUser) {
        return !usernamePresent(physicalUser) ||
               !passwordPresent(physicalUser);
    }

    private boolean put_Invalid(PhysicalUser physicalUser) {
        if(physicalUser.getId() == null)
            return true;

        return !namePresent(physicalUser) &&
               !surnamePresent(physicalUser) &&
               !contactInformationPresent(physicalUser);
    }

    private boolean contactInformationPresent(PhysicalUser physicalUser) {
        return physicalUser.getContactInformation() != null &&
                physicalUser.getContactInformation().getId() != null;
    }

    private boolean usernamePresent(PhysicalUser physicalUser) {
        return physicalUser.getUsername() != null && !physicalUser.getUsername().isBlank();
    }

    private boolean passwordPresent(PhysicalUser physicalUser) {
        return physicalUser.getPassword() != null && !physicalUser.getPassword().isBlank();
    }

    private boolean namePresent(PhysicalUser physicalUser) {
        return physicalUser.getName() != null && !physicalUser.getName().isBlank();
    }


    private boolean surnamePresent(PhysicalUser physicalUser) {
        return physicalUser.getSurname() != null && !physicalUser.getSurname().isBlank();
    }

    private ContactInformation getContactInformation(PhysicalUser physicalUser) {
        var contactInformationService = new ContactInformationService(HibernateUtils.getSession());

        if(!contactInformationPresent(physicalUser))
            return null;

        return contactInformationService.find(physicalUser.getContactInformation().getId());
    }

    @Override
    protected PhysicalUserService createService() {
        return new PhysicalUserService(HibernateUtils.getSession());
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    public final static String ENTITY_NAME = "Physical User";
}
