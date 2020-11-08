package vilnius.tech.error;

import java.util.ArrayList;
import java.util.List;

public class ExceptionUtils {

    public static List<String> unwind(Throwable cause) {
        var exceptionMessages = new ArrayList<String>();
        while(cause != null) {
            exceptionMessages.add(cause.getMessage());
            cause = cause.getCause();
        }
        return exceptionMessages;
    }

    private ExceptionUtils() { }
}
