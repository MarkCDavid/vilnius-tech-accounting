package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.ContactInformation;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.utils.PasswordUtils;

public class PhysicalUserService extends HibernateService<PhysicalUser> {

    public PhysicalUserService(Session session) {
        super(PhysicalUser.class, session);
    }

    public PhysicalUser create(String username, String password, String name, String surname, ContactInformation contactInformation) {
        var physicalUser = new PhysicalUser();
        physicalUser.setUsername(username);
        physicalUser.setSalt(PasswordUtils.getSalt(128));
        physicalUser.setPassword(PasswordUtils.generateSecurePassword(password, physicalUser.getSalt()));
        physicalUser.setName(name);
        physicalUser.setSurname(surname);
        physicalUser.setContactInformation(contactInformation);
        return update(physicalUser);
    }

}
