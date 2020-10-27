package vilnius.tech.utils;

import javafx.scene.control.ChoiceBox;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ChoiceBoxUtils {

    public static <T> void OnSelectionChanged(ChoiceBox<T> choiceBox, BiConsumer<ChoiceBox<T>, T> onChanged) {
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldIndex, newIndex) -> {
            if((Integer)newIndex == -1)
                onChanged.accept(choiceBox, null);
            else
                onChanged.accept(choiceBox, choiceBox.getItems().get((Integer)newIndex));
        });
    }

    private ChoiceBoxUtils() { }
}
