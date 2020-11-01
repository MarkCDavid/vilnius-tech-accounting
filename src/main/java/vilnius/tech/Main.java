package vilnius.tech;

import javafx.application.Application;
import javafx.stage.Stage;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import vilnius.tech.controller.DatabaseSelection;
import vilnius.tech.hibernate.*;
import vilnius.tech.utils.Database;
import vilnius.tech.view.View;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        var cfg = new Configuration().configure("hibernate.cfg.xml");
        var sessionFactory = cfg.buildSessionFactory();
        var session = sessionFactory.openSession();


        var emf = session.getEntityManagerFactory();









        var country = new Country("Lithuania", "LT");
        country = SEIV(emf, country);

        var city = new City("Vilnius", country);
        city = SEIV(emf, city);

        var address = new Address(city, "Saulėtekio al. 5", "3434");
        address = SEIV(emf, address);

        var contactInformation = new ContactInformation(address, "+3592342355342", "email@email.com");
        contactInformation = SEIV(emf, contactInformation);

        User user = new PhysicalUser("aurimas", "aurimas", "aurimas", "", contactInformation);
        user = SEIV(emf, user);

        user = new PhysicalUser("petras", "petras", "petras", "", contactInformation);
        user = SEIV(emf, user);

        user = new JuridicalUser("jurgis", "jurgis", "jurgis", address, (PhysicalUser)user);
        user = SEIV(emf, user);

        user = new PhysicalUser("kastytis", "kastytis", "kastytis", "", contactInformation);
        user = SEIV(emf, user);

        var criteriaBuilder = session.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(PhysicalUser.class);
        var root = query.from(PhysicalUser.class);
        query.select(root);

        var results = session.createQuery(query).getResultList();
        for(var result : results) {
            System.out.println(result.getName());
        }

        new View(new DatabaseSelection(), primaryStage, "Select Database", "databaseSelection.fxml").render();
    }

    private <T> T SEIV(EntityManagerFactory emf, T o) {
        var em = emf.createEntityManager();
        em.getTransaction().begin();

        var merged = em.merge(o);
        em.persist(merged);
        em.getTransaction().commit();
        em.close();

        return merged;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
