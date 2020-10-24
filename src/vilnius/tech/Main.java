package vilnius.tech;

import javafx.application.Application;
import javafx.stage.Stage;
import vilnius.tech.controller.DatabaseSelection;
import vilnius.tech.view.View;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        new View(new DatabaseSelection(), primaryStage, "Select Database").render("databaseSelection.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
