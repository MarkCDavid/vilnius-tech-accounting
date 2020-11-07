package vilnius.tech.seeds;

import org.hibernate.Session;

public interface Seeder {

    void seed(Session session);
}
