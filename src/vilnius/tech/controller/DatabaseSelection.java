package vilnius.tech.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import vilnius.tech.seeds.CountryCitySeeder;
import vilnius.tech.session.Serializer;
import vilnius.tech.session.Session;
import vilnius.tech.session.SessionSerializationException;
import vilnius.tech.utils.MessageBox;
import vilnius.tech.view.View;

import java.io.File;
import java.io.IOException;

public class DatabaseSelection extends Controller {

    @FXML
    Button btnOpen;

    public void buttonSelectDatabaseAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select database");
        File file = fileChooser.showOpenDialog(getStage());

        if(file == null)
            return;

        Session session = openDatabase(file);

        if(session == null)
            return;

        getStage().setOnCloseRequest(windowEvent -> saveDatabase(session, file));
        switchToLogin(session);
    }

    public void buttonNewDatabaseAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save database to");
        File file = fileChooser.showSaveDialog(getStage());

        if(file == null)
            return;

        Session session = new Session();
        new CountryCitySeeder().seed(session);
        if(!saveDatabase(session, file))
            return;

        getStage().setOnCloseRequest(windowEvent -> saveDatabase(session, file));
        switchToLogin(session);
    }

    public void switchToLogin(Session session) throws IOException {
        new View(new GatewayController(session), getStage(), "Gateway").render("gateway.fxml");

    }

    private Session openDatabase(File file) {
        try {
            return Serializer.loadSession(file.getPath());
        } catch (SessionSerializationException e) {
            MessageBox.show(Alert.AlertType.ERROR, "Invalid Database", String.format("File '%s' is not a valid database.", file.getPath()));
            return null;
        }
    }

    private boolean saveDatabase(Session session, File file) {
        try {
            Serializer.saveSession(session, file.getPath());
            return true;
        } catch (SessionSerializationException e) {
            MessageBox.show(Alert.AlertType.ERROR, "Save failure", String.format("Failed to save the database to file '%s'.", file.getPath()));
            return false;
        }
    }
}
