package vilnius.tech.view.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import vilnius.tech.Main;
import vilnius.tech.error.DatabaseExceptionPolicy;
import vilnius.tech.seeds.CategorySeeder;
import vilnius.tech.seeds.CountryCitySeeder;
import vilnius.tech.seeds.ExpenseTypeSeeder;
import vilnius.tech.seeds.IncomeTypeSeeder;
import vilnius.tech.utils.Parameters;
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
                cfg.setProperty("hibernate.connection.password", passwordFieldDatabasePassword.getText());
            }

            cfg.setProperty("hibernate.hbm2ddl.auto", Parameters.getDropDatabase() ? "create-drop" : "update");

            var sessionFactory = cfg.buildSessionFactory();
            var session = sessionFactory.openSession();

            if(Parameters.getDropDatabase()) {
                new CountryCitySeeder().seed(session);
                new CategorySeeder().seed(session);
                new IncomeTypeSeeder().seed(session);
                new ExpenseTypeSeeder().seed(session);
            }

            switchToLogin(session);
        }
        catch (Exception ex) {
            var applicationError = DatabaseExceptionPolicy.apply(ex);
            if(applicationError == null)
                throw ex;

            var errorRouter = getView().getErrorRouter();
            if(errorRouter == null)
                throw ex;

            errorRouter.route(applicationError);
        }
        finally {
            progressIndicator.setVisible(false);
        }
    }

    private boolean hasCustomConfiguration() {
        return textFieldDatabaseAddress.getText().length() > 0 ||
                textFieldDatabaseUsername.getText().length() > 0 ||
                passwordFieldDatabasePassword.getText().length() > 0;
    }

    public void switchToLogin(Session session) throws IOException {
        var view = new View(new GatewayController(session), getStage(), "Gateway", "gateway.fxml");
        view.setErrorRouter(getView().getErrorRouter());
        view.render();
    }

    @FXML
    public void initialize() {
        progressIndicator.setVisible(false);
    }

    @FXML public TextField textFieldDatabaseAddress;
    @FXML public TextField textFieldDatabaseUsername;
    @FXML public PasswordField passwordFieldDatabasePassword;
    @FXML public ProgressIndicator progressIndicator;
}
