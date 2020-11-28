package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.error.DatabaseExceptionPolicy;
import vilnius.tech.error.router.JsonMessageRouter;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.User;
import vilnius.tech.web.controller.proxy.controller.FinancialCategoryProxy;

@RestController
public class FinancialCategoryController extends WebApiCRUDController<FinancialCategory, FinancialCategoryProxy> {

    protected FinancialCategoryController() {
        super(new FinancialCategoryProxy(), new JsonMessageRouter());
    }

    @Override
    @GetMapping(path = "/financialCategory/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        return super.getAll(take, skip);
    }

    @Override
    @GetMapping(path = "/financialCategory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable(name="id") Integer id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(path = "/financialCategory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        return super.delete(id);
    }


    @Override
    @PostMapping(path = "/financialCategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody FinancialCategory financialCategory) {
        return super.post(financialCategory);
    }

    @Override
    @PutMapping(path = "/financialCategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody FinancialCategory financialCategory) {
        return super.put(financialCategory);
    }

    @GetMapping(path = "/financialCategory/{id}/responsibleUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get_ResponsibleUser(@PathVariable(name = "id") Integer id) {
        try {
            return getControllerProxy().get_ResponsibleUser(id);
        } catch (Exception ex) {
            var error = DatabaseExceptionPolicy.apply(ex);
            if(error == null)
                throw ex;

            if(getErrorRouter() == null)
                throw ex;

            return getErrorRouter().route(error);
        }
    }



    @GetMapping(path = "/financialCategory/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get_ResponsibleFor(@PathVariable(name = "id") Integer id) {
        try {
            return getControllerProxy().get_ResponsibleFor(id);
        } catch (Exception ex) {
            var error = DatabaseExceptionPolicy.apply(ex);
            if(error == null)
                throw ex;

            if(getErrorRouter() == null)
                throw ex;

            return getErrorRouter().route(error);
        }
    }

    @PutMapping(path = "/financialCategory/{id}/responsibleUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put_ResponsibleUser(@PathVariable(name = "id") Integer id, @RequestBody User user) {
        try {
            return getControllerProxy().put_ResponsibleUser(id, user);
        } catch (Exception ex) {
            var error = DatabaseExceptionPolicy.apply(ex);
            if(error == null)
                throw ex;

            if(getErrorRouter() == null)
                throw ex;

            return getErrorRouter().route(error);
        }
    }

    @DeleteMapping(path = "/financialCategory/{id}/responsibleUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete_ResponsibleUser(@PathVariable(name = "id") Integer id, @RequestBody User user) {
        try {
            return getControllerProxy().delete_ResponsibleUser(id, user);
        } catch (Exception ex) {
            var error = DatabaseExceptionPolicy.apply(ex);
            if(error == null)
                throw ex;

            if(getErrorRouter() == null)
                throw ex;

            return getErrorRouter().route(error);
        }
    }
}
