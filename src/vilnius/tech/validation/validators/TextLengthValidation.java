package vilnius.tech.validation.validators;

import javafx.scene.control.TextInputControl;
import vilnius.tech.validation.Validation;
import vilnius.tech.validation.ValidationResult;

public class TextLengthValidation implements Validation {

    public TextLengthValidation(TextInputControl control, int min, int max) {
        this.control = control;
        this.min = Math.max(min, 0);
        this.max = Math.max(max, 0);
    }

    public TextLengthValidation(TextInputControl control, int min) {
        this(control, min, 0);
    }

    @Override
    public ValidationResult validate() {
        String text = control.getText();

        if(min > 0 && text.length() < min)
            return ValidationResult.BAD(String.format("%s text input too short. Got %s. Expected %s.", control.getAccessibleText(), text.length(), min));

        if(max > 0 && text.length() >= max)
            return ValidationResult.BAD(String.format("%s text input too long. Got %s. Expected %s.", control.getAccessibleText(), text.length(), max));

        return ValidationResult.OK();
    }

    private final int min;
    private final int max;
    private final TextInputControl control;

}
