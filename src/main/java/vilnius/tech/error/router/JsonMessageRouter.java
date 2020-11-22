package vilnius.tech.error.router;

import org.springframework.http.ResponseEntity;
import vilnius.tech.error.ApplicationError;
import vilnius.tech.error.ErrorRouter;
import vilnius.tech.web.controller.utils.JsonResponseUtils;

public class JsonMessageRouter implements ErrorRouter<ResponseEntity<String>> {

    @Override
    public ResponseEntity<String> route(ApplicationError error) {
        return JsonResponseUtils.BAD(error.getMessage());
    }
}
