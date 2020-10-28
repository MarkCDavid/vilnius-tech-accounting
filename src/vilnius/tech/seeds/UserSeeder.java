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
