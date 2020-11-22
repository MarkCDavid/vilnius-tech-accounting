package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.error.router.JsonMessageRouter;
import vilnius.tech.hibernate.ExpenseType;
import vilnius.tech.web.controller.proxy.controller.ExpenseTypeProxy;

@RestController
public class ExpenseTypeController extends WebApiCRUDController<ExpenseType, ExpenseTypeProxy> {

    protected ExpenseTypeController() {
        super(new ExpenseTypeProxy(), new JsonMessageRouter());
    }

    @Override
    @GetMapping(path = "/expenseType/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        return super.getAll(take, skip);
    }

    @Override
    @GetMapping(path = "/expenseType/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable(name="id") Integer id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(path = "/expenseType/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        return super.delete(id);
    }


    @PostMapping(path = "/expenseType", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody ExpenseType expenseType) {
        return super.post(expenseType);
    }

    @Override
    @PutMapping(path = "/expenseType", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody ExpenseType expenseType) {
        return super.put(expenseType);
    }

}
