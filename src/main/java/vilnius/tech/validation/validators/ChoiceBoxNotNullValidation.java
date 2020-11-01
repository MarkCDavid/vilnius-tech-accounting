package vilnius.tech.validation.validators;

import javafx.scene.control.ChoiceBox;
import vilnius.tech.validation.Validation;
import vilnius.tech.validation.ValidationResult;

public class ChoiceBoxNotNullValidation<T> implements Validation {

    public ChoiceBoxNotNullValidation(ChoiceBox<T> control) {
        this.control = control;
    }
    @Override
    public ValidationResult validate() {
        return control.getValue() != null ?
            ValidationResult.OK() :
            ValidationResult.BAD(String.format("%s has no value selected!", control.getAccessibleText()));
    }

    private final ChoiceBox<T> control;
}
