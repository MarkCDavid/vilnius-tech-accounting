package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.error.DatabaseExceptionPolicy;
import vilnius.tech.error.router.JsonMessageRouter;
import vilnius.tech.hibernate.Expense;
import vilnius.tech.web.controller.proxy.controller.ExpenseProxy;

@RestController
public class ExpenseController extends WebApiCRUDController<Expense, ExpenseProxy> {

    protected ExpenseController() {
        super(new ExpenseProxy(), new JsonMessageRouter());
    }

    @Override
    @GetMapping(path = "/expense/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        return super.getAll(take, skip);
    }

    @Override
    @GetMapping(path = "/expense/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable(name="id") Integer id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(path = "/expense/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        return super.delete(id);
    }


    @Override
    @PostMapping(path = "/expense", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody Expense expense) {
        return super.post(expense);
    }

    @Override
    @PutMapping(path = "/expense", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody Expense expense) {
        return super.put(expense);
    }

    @GetMapping(path = "/expense/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get_Category(@PathVariable(name="id") Integer id) {
        try {
            return getControllerProxy().get_Category(id);
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
