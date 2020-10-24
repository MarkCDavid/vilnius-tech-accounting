package vilnius.tech.controller;

import javafx.stage.Stage;

public abstract class Controller {

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    private Stage stage;
}
