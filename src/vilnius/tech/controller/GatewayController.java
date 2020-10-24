package vilnius.tech.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import vilnius.tech.dal.PhysicalUser;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.utils.MessageBox;
import vilnius.tech.view.View;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GatewayController extends SessionController {

    public GatewayController(Session session) {
        super(session);
        var user = new PhysicalUser(getSession());
        user.setUsername("admin");
        user.setPassword("password");
    }

    @FXML
    public TextField textFieldUsername;

    @FXML
    public PasswordField passwordFieldPassword;

    public void buttonSignInAction(ActionEvent actionEvent) throws IOException {

        if(textFieldUsername.getText().length() == 0)
        {
            MessageBox.show(Alert.AlertType.WARNING, "No username specified", "Please specify username of the account you want to log in.");
            return;
        }

        if(passwordFieldPassword.getText().length() == 0)
        {
            MessageBox.show(Alert.AlertType.WARNING, "No password specified", "Please specify password of the account you want to log in.");
            return;
        }

        List<User> users = getSession().query(User.class, this::matchPassword);

        if(users.size() != 1) {
            MessageBox.show(Alert.AlertType.WARNING, "No such user", "Username or password incorrect.");
            return;
        }

        new View(new MainController(users.get(0), getSession()), getStage(), "Main").render("main.fxml");

    }

    public boolean matchPassword(User user) {
        return Objects.equals(user.getUsername(), textFieldUsername.getText()) &&
               Objects.equals(user.getPassword(), passwordFieldPassword.getText());
    }
}
