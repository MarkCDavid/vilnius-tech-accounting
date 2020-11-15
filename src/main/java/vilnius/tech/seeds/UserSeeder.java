package vilnius.tech.seeds;

import org.hibernate.Session;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.hibernate.service.PhysicalUserService;

public class UserSeeder implements Seeder {

    @Override
    public void seed(Session session) {
        createPhysicalUser(session, "admin", "admin", "admin", "");
        createPhysicalUser(session, "Aurimas", "Aurimas", "Aurimas", "");
        createPhysicalUser(session, "Jurgis", "Jurgis", "Jurgis", "");
        createPhysicalUser(session, "Arminas", "Arminas", "Arminas", "");
        createPhysicalUser(session, "Lietengis", "Lietengis", "Lietengis", "");
        createPhysicalUser(session, "Vargis", "Vargis", "Vargis", "");
    }


    private PhysicalUser createPhysicalUser(Session session, String username, String password, String name, String surname) {
        var controller = new PhysicalUserService(session);
        return controller.create(username, password, name, surname, null);
    }
}
