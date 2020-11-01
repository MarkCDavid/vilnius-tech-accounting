package vilnius.tech;

import javafx.application.Application;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import vilnius.tech.controller.DatabaseSelection;
import vilnius.tech.hibernate.*;
import vilnius.tech.utils.Database;
import vilnius.tech.view.View;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        var cfg = new Configuration().configure("hibernate.cfg.xml");
        var services = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        var sessionFactory = cfg.buildSessionFactory();
        var session = sessionFactory.openSession();

        var country = new Country("Lithuania", "LT");
        var emf = session.getEntityManagerFactory();
        var em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(em.merge(country));
        em.getTransaction().commit();
        em.close();



        new View(new DatabaseSelection(), primaryStage, "Select Database", "databaseSelection.fxml").render();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
