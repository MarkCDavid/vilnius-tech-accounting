package vilnius.tech.view.controller.crud;

import vilnius.tech.dal.City;
import vilnius.tech.dal.PhysicalUser;
import vilnius.tech.session.Session;

public class PhysicalUserCU {

    public static PhysicalUser create(Session session, String username, String password, City city, String street, String postal, String phoneNumber, String email, String name, String surname) {
        var user = new PhysicalUser(session);
        update(user, username, password, city, street, postal, phoneNumber, email, name, surname);
        return user;
    }

    public static void update(PhysicalUser user, String username, String password, City city, String street, String postal, String phoneNumber, String email, String name, String surname) {
        user.setUsername(username);
        user.setPassword(password);
        user.setContactInformation(ContactInformationCU.create(user.getSession(), city, street, postal, phoneNumber, email));
        user.setName(name);
        user.setSurname(surname);
    }

    private PhysicalUserCU() { }
}
