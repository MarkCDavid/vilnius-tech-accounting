package vilnius.tech.web.utils;

public class Messages {

    public static String itemNotFound(String type, Integer id) {
        return String.format("Item of type '%s' with id '%s' not found!", type, id);
    }

    public static String itemNotFound_Field(String type, String field, String value) {
        return String.format("Item of type '%s' with '%s: %s' not found!", type, field, value);
    }

    public static String deleteSuccessful(String type, Integer id) {
        return String.format("Item of type '%s' with id '%s' successfully deleted!", type, id);
    }

    public static String invalidData(String type) {
        return String.format("Data for item of type '%s' is invalid!", type);
    }

    public static String invalidUsernameOrPassword() {
        return "Invalid username or password.";
    }

    public static String invalidDateFormat(String parameter) {
        return String.format("Date format of field '%s' is invalid!", parameter);
    }

    private Messages() { }
}
