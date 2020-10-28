package vilnius.tech.seeds;

import vilnius.tech.dal.Country;
import vilnius.tech.dal.FinancialCategory;
import vilnius.tech.dal.PhysicalUser;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;

public class UserSeeder implements Seeder {

    @Override
    public void seed(Session session) {
        createPhysicalUser(session, "administrator", "administrator", "Admin", "Istrator");
        createPhysicalUser(session, "Aurimas", "Aurimas", "Aurimas", "");
        createPhysicalUser(session, "Jurgis", "Jurgis", "Jurgis", "");
        createPhysicalUser(session, "Arminas", "Arminas", "Arminas", "");
        createPhysicalUser(session, "Lietengis", "Lietengis", "Lietengis", "");
        createPhysicalUser(session, "Vargis", "Vargis", "Vargis", "");
    }


    private PhysicalUser createPhysicalUser(Session session, String username, String password, String name, String surname) {
        PhysicalUser user = new PhysicalUser(session);
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        return user;
    }
}
