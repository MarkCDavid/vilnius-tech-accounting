package vilnius.tech.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vilnius.tech.view.controller.modal.ModalController;

import java.io.IOException;

public class Modal<R> {

    protected final String title;
    protected final String path;
    protected final ModalController<R> controller;
    protected final View owner;

    public Modal(ModalController<R> controller, View owner, String title, String path) {
        this.title = title;
        this.path = Constants.FXML_PATH + path;
        this.controller = controller;
        this.owner = owner;
        this.controller.setModal(this);
    }

    public R render() throws IOException {
        Stage stage = new Stage();
        this.controller.setStage(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.path));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();

        stage.setScene(new Scene(root));
        stage.setTitle(this.title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner.getPrimaryStage().getOwner());
        stage.showAndWait();

        return this.controller.getModalResult();
    }


}

