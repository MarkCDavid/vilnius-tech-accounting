package vilnius.tech.utils;

import javafx.scene.control.Alert;

public class MessageBox {


    public static void show(Alert.AlertType type, String title, String body) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(body);
        alert.show();
    }

    private MessageBox() { }
}
