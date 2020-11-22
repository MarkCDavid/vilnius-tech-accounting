package vilnius.tech.web.controller.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import vilnius.tech.seeds.*;

public class HibernateUtils {

    private static Session session;
    private static SessionFactory sessionFactory;
    private static Configuration configuration;

    public static Session getSession() {
        if(configuration == null) {
            configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            sessionFactory = configuration.buildSessionFactory();
        }

        if(session == null || !session.isOpen()) {
            session = sessionFactory.openSession();
        }

        return session;
    }

    private HibernateUtils() {}
}
