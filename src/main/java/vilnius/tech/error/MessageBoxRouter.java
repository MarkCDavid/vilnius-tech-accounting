package vilnius.tech.error;

import javafx.scene.control.Alert;
import vilnius.tech.utils.MessageBox;

public class MessageBoxRouter implements ErrorRouter {
    @Override
    public void route(ApplicationError error) {
        MessageBox.show(Alert.AlertType.ERROR, error.getTitle(), error.getMessage());
    }
}
