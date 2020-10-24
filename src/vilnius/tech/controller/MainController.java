package vilnius.tech.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;

public class MainController extends SessionController {

    public MainController(User user, Session session) {
        super(session);
        this.user = user;
    }

    @FXML
    public void initialize() {
        labelTemp.setText(user.getUsername());
    }

    @FXML
    Label labelTemp;


    private final User user;
}
