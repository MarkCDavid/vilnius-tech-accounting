package vilnius.tech.validation.validators;

import javafx.scene.control.TextInputControl;
import vilnius.tech.validation.Validation;
import vilnius.tech.validation.ValidationResult;

public class TextLongFormatValidation implements Validation {

    public TextLongFormatValidation(TextInputControl control) {
        this.control = control;
    }

    @Override
    public ValidationResult validate() {
        String text = control.getText();
        try {
            Long.parseLong(text);
            return ValidationResult.OK();
        }
        catch (NumberFormatException exception) {
            return ValidationResult.BAD(String.format("Invalid format of %s.", control.getAccessibleText()));
        }
    }

    private final TextInputControl control;

}
