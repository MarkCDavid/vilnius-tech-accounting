package vilnius.tech.error;

public class ApplicationError {

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public ApplicationError(String title, String message) {
        this.title = title;
        this.message = message;
    }

    private final String title;

    private final String message;
}
