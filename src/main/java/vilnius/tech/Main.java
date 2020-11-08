package vilnius.tech;

import javafx.application.Application;
import javafx.stage.Stage;
import vilnius.tech.view.controller.DatabaseSelection;
import vilnius.tech.error.MessageBoxRouter;
import vilnius.tech.view.View;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        var view = new View(new DatabaseSelection(), primaryStage, "Select Database", "databaseSelection.fxml");
        view.setErrorRouter(new MessageBoxRouter());
        view.render();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
