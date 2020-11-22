package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.error.router.JsonMessageRouter;
import vilnius.tech.hibernate.IncomeType;
import vilnius.tech.web.controller.proxy.controller.IncomeTypeProxy;

@RestController
public class IncomeTypeController extends WebApiCRUDController<IncomeType, IncomeTypeProxy> {

    protected IncomeTypeController() {
        super(new IncomeTypeProxy(), new JsonMessageRouter());
    }

    @Override
    @GetMapping(path = "/incomeType/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        return super.getAll(take, skip);
    }

    @Override
    @GetMapping(path = "/incomeType/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable(name="id") Integer id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(path = "/incomeType/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        return super.delete(id);
    }


    @PostMapping(path = "/incomeType", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody IncomeType incomeType) {
        return super.post(incomeType);
    }

    @Override
    @PutMapping(path = "/incomeType", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody IncomeType incomeType) {
        return super.put(incomeType);
    }

}
