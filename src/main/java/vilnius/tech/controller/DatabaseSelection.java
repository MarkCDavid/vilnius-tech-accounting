package vilnius.tech.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import vilnius.tech.seeds.CategorySeeder;
import vilnius.tech.seeds.CountryCitySeeder;
import vilnius.tech.seeds.ExpenseTypeSeeder;
import vilnius.tech.seeds.IncomeTypeSeeder;
import vilnius.tech.utils.MessageBox;
import vilnius.tech.view.View;

import java.io.IOException;

public class DatabaseSelection extends Controller {

    public void buttonConnectToDatabase(ActionEvent actionEvent) throws IOException {

        try {
            progressIndicator.setVisible(true);

            var cfg = new Configuration().configure("hibernate.cfg.xml");

            if (hasCustomConfiguration()) {
                cfg.setProperty("hibernate.connection.url", textFieldDatabaseAddress.getText());
                cfg.setProperty("hibernate.connection.username", textFieldDatabaseUsername.getText());
                cfg.setProperty("hibernate.connection.password", textFieldDatabasePassword.getText());
            }

            var sessionFactory = cfg.buildSessionFactory();
            var session = sessionFactory.openSession();

            new CountryCitySeeder().seed(session);
            new CategorySeeder().seed(session);
            new IncomeTypeSeeder().seed(session);
            new ExpenseTypeSeeder().seed(session);

            switchToLogin(session);
        }
        catch (Exception ex) {
            MessageBox.show(Alert.AlertType.ERROR, "Connection error", "Connection to the database failed.");
        }
        finally {
            progressIndicator.setVisible(false);
        }
    }

    private boolean hasCustomConfiguration() {
        return textFieldDatabaseAddress.getText().length() > 0 ||
                textFieldDatabaseUsername.getText().length() > 0 ||
                textFieldDatabasePassword.getText().length() > 0;
    }

    public void switchToLogin(Session session) throws IOException {
        new View(new GatewayController(session), getStage(), "Gateway", "gateway.fxml").render();
    }

    @FXML
    public void initialize() {
        progressIndicator.setVisible(false);
    }

    @FXML public TextField textFieldDatabaseAddress;
    @FXML public TextField textFieldDatabaseUsername;
    @FXML public TextField textFieldDatabasePassword;
    @FXML public ProgressIndicator progressIndicator;
}
