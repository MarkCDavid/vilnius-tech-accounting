package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.error.DatabaseExceptionPolicy;
import vilnius.tech.error.ErrorRouter;
import vilnius.tech.error.router.JsonMessageRouter;
import vilnius.tech.hibernate.Expense;
import vilnius.tech.hibernate.Flow;
import vilnius.tech.web.controller.proxy.controller.ExpenseProxy;
import vilnius.tech.web.controller.proxy.controller.FlowProxy;
import vilnius.tech.web.controller.proxy.model.FlowParameters;

@RestController
public class FlowController {

    protected FlowController() {
        controllerProxy = new FlowProxy();
        errorRouter = new JsonMessageRouter();
    }

    @PostMapping(path = "/flow/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get_Category(@PathVariable(name="id") Integer id, @RequestBody FlowParameters flowParameters) {
        try {
            return getControllerProxy().get_FlowData(id, flowParameters);
        } catch (Exception ex) {
            var error = DatabaseExceptionPolicy.apply(ex);
            if(error == null)
                throw ex;

            if(getErrorRouter() == null)
                throw ex;

            return getErrorRouter().route(error);
        }
    }

    public FlowProxy getControllerProxy() {
        return controllerProxy;
    }

    public ErrorRouter<ResponseEntity<String>> getErrorRouter() {
        return errorRouter;
    }

    private final ErrorRouter<ResponseEntity<String>> errorRouter;
    private final FlowProxy controllerProxy;
}
