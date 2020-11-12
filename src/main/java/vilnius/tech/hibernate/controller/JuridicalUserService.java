package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.JuridicalUser;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.utils.PasswordUtils;

public class JuridicalUserService extends HibernateService<JuridicalUser> {

    public JuridicalUserService(Session session) {
        super(JuridicalUser.class, session);
    }

    public JuridicalUser create(String username, String password, String name, Address address, PhysicalUser contactUser) {
        var juridicalUser = new JuridicalUser();
        juridicalUser.setUsername(username);
        juridicalUser.setSalt(PasswordUtils.getSalt(128));
        juridicalUser.setPassword(PasswordUtils.generateSecurePassword(password, juridicalUser.getSalt()));
        juridicalUser.setName(name);
        juridicalUser.setAddress(address);
        juridicalUser.setContactUser(contactUser);
        return update(juridicalUser);
    }

}
