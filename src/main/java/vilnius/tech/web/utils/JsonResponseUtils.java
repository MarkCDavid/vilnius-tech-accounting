package vilnius.tech.web.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonResponseUtils {

    public static ResponseEntity<String> RESPONSE(HttpStatus status, Object object) {
        return ResponseEntity.status(status).body(
                GsonUtils.getGson().toJson(object)
        );
    }

    public static ResponseEntity<String> OK(Object object) {
        return RESPONSE(HttpStatus.OK, object);
    }

    public static ResponseEntity<String> BAD(String message) {
        return RESPONSE(HttpStatus.BAD_REQUEST, new Message(message));
    }

    private JsonResponseUtils() { }
}
