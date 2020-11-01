package vilnius.tech.validation;

public class ValidationResult {

    public ValidationResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }

    private final boolean isValid;
    private final String message;

    public static ValidationResult OK() {
        return new ValidationResult(true, "");
    }

    public static ValidationResult BAD(String message) {
        return new ValidationResult(false, message);
    }

}
