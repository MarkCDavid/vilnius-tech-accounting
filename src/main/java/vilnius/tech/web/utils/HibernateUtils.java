package vilnius.tech.web.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static Session session;
    private static SessionFactory sessionFactory;
    private static Configuration configuration;

    public static Session getSession() {
        if(configuration == null) {
            configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        }

        if(sessionFactory == null) {
            sessionFactory = configuration.buildSessionFactory();
        }

        return sessionFactory.openSession();
    }

    private HibernateUtils() {}
}
