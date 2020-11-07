package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.JuridicalUser;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.session.HibernateController;

public class JuridicalUserController extends HibernateController<JuridicalUser> {

    public JuridicalUserController(Session session) {
        super(JuridicalUser.class, session);
    }

    public JuridicalUser create(String username, String password, String name, Address address, PhysicalUser contactUser) {
        var juridicalUser = new JuridicalUser();
        juridicalUser.setUsername(username);
        juridicalUser.setPassword(password);
        juridicalUser.setName(name);
        juridicalUser.setAddress(address);
        juridicalUser.setContactUser(contactUser);
        return update(juridicalUser);
    }

}
