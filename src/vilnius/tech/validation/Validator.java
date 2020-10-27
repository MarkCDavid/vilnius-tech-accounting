package vilnius.tech.validation;

import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import vilnius.tech.utils.MessageBox;

import java.util.*;
import java.util.stream.Collectors;

public class Validator {

    public void register(int priority, Validation validation) {
        if(!validations.containsKey(priority))
            validations.put(priority, new ArrayList<>());

        this.validations.get(priority).add(validation);
    }

    public boolean validate() {
        List<String> validationMessages = new ArrayList<>();
        List<Integer> priorities = validations.keySet().stream().sorted().collect(Collectors.toList());
        for(var priority: priorities){
            if(!validationMessages.isEmpty())
                break;

            for(var validation: validations.get(priority)) {
                ValidationResult validationResult = validation.validate();
                if (!validationResult.isValid())
                    validationMessages.add(validationResult.getMessage());
            }
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


    private final Map<Integer, List<Validation>> validations = new HashMap<>();
}
