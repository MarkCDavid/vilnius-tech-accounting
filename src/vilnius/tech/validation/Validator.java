package vilnius.tech.validation;

import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import vilnius.tech.utils.MessageBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public void register(Validation validation) {
        this.validations.add(validation);
    }

    public boolean validate() {
        List<String> validationMessages = new ArrayList<>();
        for(var validation: validations){
            ValidationResult validationResult = validation.validate();
            if(!validationResult.isValid())
                validationMessages.add(validationResult.getMessage());
        }
        if(validationMessages.isEmpty())
            return true;

        MessageBox.show(
                Alert.AlertType.ERROR,
                "Validation error!",
                String.format("Failed to validate input data due to: %n%s", String.join("\n", validationMessages))
        );
        return false;
    }


    private final List<Validation> validations = new ArrayList<>();
}
