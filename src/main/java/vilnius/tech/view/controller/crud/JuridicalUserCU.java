package vilnius.tech.view.controller.crud;

import vilnius.tech.dal.City;
import vilnius.tech.dal.JuridicalUser;
import vilnius.tech.session.Session;

public class JuridicalUserCU {

    public static JuridicalUser create(Session session, String username, String password, City city, String street, String postal, String phoneNumber, String email, String name, String surname, String juridicalName, City juridicalCity, String juridicalStreet, String juridicalPostal) {
        var user = new JuridicalUser(session);
        update(user, username, password, city, street, postal, phoneNumber, email, name, surname, juridicalName, juridicalCity, juridicalStreet, juridicalPostal);
        return user;
    }

    public static void update(JuridicalUser user, String username, String password, City city, String street, String postal, String phoneNumber, String email, String name, String surname, String juridicalName, City juridicalCity, String juridicalStreet, String juridicalPostal) {
        user.setUsername(username);
        user.setPassword(password);
        user.setContactUser(PhysicalUserCU.create(user.getSession(), null, null, city, street, postal, phoneNumber, email, name, surname));
        user.setName(juridicalName);
        user.setAddress(AddressCU.create(user.getSession(), juridicalCity, juridicalStreet, juridicalPostal));
    }

    private JuridicalUserCU() { }
}
