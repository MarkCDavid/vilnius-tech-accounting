package vilnius.tech.controller;

import javafx.stage.Stage;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;

public abstract class ModalController<T, R> {

    public abstract R getModalResult();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Modal<T> getModal() {
        return modal;
    }

    public void setModal(Modal<T> modal) {
        this.modal = modal;
    }

    private Stage stage;

    private Modal<T> modal;
}
