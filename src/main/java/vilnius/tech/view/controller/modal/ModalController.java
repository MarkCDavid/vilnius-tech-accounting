package vilnius.tech.view.controller.modal;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import vilnius.tech.view.Modal;

public abstract class ModalController<R> {

    public ModalController() {

    }

    public ModalController(R initialValue) {
        this.result = initialValue;
    }

    public abstract boolean constructModalResult();

    public R getModalResult() {
        return this.result;
    }

    public void setModalResult(R result) {
        this.result = result;
    }

    protected abstract R createModalResult();

    @FXML
    public void onSave() {
        if(getModalResult() == null)
            setModalResult(createModalResult());

        if(!constructModalResult())
            return;

        getStage().close();
    }

    @FXML
    void onCancel() {
        this.result = null;
        getStage().close();
    }



    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public Modal<R> getModal() {
        return modal;
    }

    public void setModal(Modal<R> modal) {
        this.modal = modal;
    }

    private Stage stage;

    private Modal<R> modal;

    private R result;
}
