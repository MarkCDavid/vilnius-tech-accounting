package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.ContactInformation;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.session.HibernateController;

public class PhysicalUserController extends HibernateController<PhysicalUser> {

    public PhysicalUserController(Session session) {
        super(PhysicalUser.class, session);
    }

    public PhysicalUser create(String username, String password, String name, String surname, ContactInformation contactInformation) {
        var physicalUser = new PhysicalUser();
        physicalUser.setUsername(username);
        physicalUser.setPassword(password);
        physicalUser.setName(name);
        physicalUser.setSurname(surname);
        physicalUser.setContactInformation(contactInformation);
        return update(physicalUser);
    }

}
