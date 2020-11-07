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
        new View(new DatabaseSelection(), primaryStage, "Select Database", "databaseSelection.fxml").render();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
