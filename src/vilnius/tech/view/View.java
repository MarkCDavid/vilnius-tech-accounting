package vilnius.tech.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vilnius.tech.controller.Controller;

import java.io.IOException;

public class View {

    protected static final String FXML_PATH = "/vilnius/tech/fxml/";
    protected final Stage primaryStage;
    protected final String title;
    protected final Controller controller;

    public View(Controller controller, Stage stage, String title) {
        this.primaryStage = stage;
        this.title = title;
        this.controller = controller;
        this.controller.setStage(stage);
    }

    public void render(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH + path));
        System.out.println(FXML_PATH + path);
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle(String.format("Accounting | %s", title));
        primaryStage.setScene(new Scene(root));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}