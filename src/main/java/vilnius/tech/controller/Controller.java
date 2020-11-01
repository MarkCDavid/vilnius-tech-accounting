package vilnius.tech.controller;

import javafx.stage.Stage;
import vilnius.tech.view.View;

public abstract class Controller {

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    private Stage stage;

    private View view;
}
