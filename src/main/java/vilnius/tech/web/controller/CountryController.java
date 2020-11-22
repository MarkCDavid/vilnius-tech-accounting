package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.error.router.JsonMessageRouter;
import vilnius.tech.hibernate.Country;
import vilnius.tech.web.controller.proxy.CountryProxy;

@RestController
public class CountryController extends WebApiCRUDController<Country> {

    protected CountryController() {
        super(new CountryProxy(), new JsonMessageRouter());
    }

    @Override
    @GetMapping(path = "/country/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        return super.getAll(take, skip);
    }

    @Override
    @GetMapping(path = "/country/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable(name="id") Integer id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(path = "/country/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        return super.delete(id);
    }


    @PostMapping(path = "/country", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody Country country) {
        return super.post(country);
    }

    @Override
    @PutMapping(path = "/country", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody Country country) {
        return super.put(country);
    }

}
