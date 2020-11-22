package vilnius.tech.error.router;

import javafx.scene.control.Alert;
import vilnius.tech.error.ApplicationError;
import vilnius.tech.error.ErrorRouter;
import vilnius.tech.utils.MessageBox;

import java.util.Objects;

public class MessageBoxRouter implements ErrorRouter<Object> {
    @Override
    public Object route(ApplicationError error) {
        MessageBox.show(Alert.AlertType.ERROR, error.getTitle(), error.getMessage());
        return null;
    }
}
