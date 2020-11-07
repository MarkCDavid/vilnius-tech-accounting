package vilnius.tech.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vilnius.tech.controller.Controller;

import java.io.IOException;

public class View {

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    protected final Stage primaryStage;
    protected final String title;
    protected final Controller controller;
    protected final String path;


    public View(Controller controller, Stage stage, String title, String path) {
        this.primaryStage = stage;
        this.title = title;
        this.path = Constants.FXML_PATH + path;
        this.controller = controller;
        this.controller.setStage(primaryStage);
        this.controller.setView(this);
    }

    public void render() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.path));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle(String.format("Accounting | %s", title));
        primaryStage.setScene(new Scene(root));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}