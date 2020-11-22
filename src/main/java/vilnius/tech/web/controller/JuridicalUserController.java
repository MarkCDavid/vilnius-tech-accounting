package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.error.router.JsonMessageRouter;
import vilnius.tech.hibernate.JuridicalUser;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.web.controller.proxy.controller.JuridicalUserProxy;
import vilnius.tech.web.controller.proxy.controller.PhysicalUserProxy;

@RestController
public class JuridicalUserController extends WebApiCRUDController<JuridicalUser, JuridicalUserProxy> {

    protected JuridicalUserController() {
        super(new JuridicalUserProxy(), new JsonMessageRouter());
    }

    @Override
    @GetMapping(path = "/juridicalUser/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        return super.getAll(take, skip);
    }

    @Override
    @GetMapping(path = "/juridicalUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable(name="id") Integer id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(path = "/juridicalUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        return super.delete(id);
    }

    @Override
    @PostMapping(path = "/juridicalUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody JuridicalUser juridicalUser) {
        return super.post(juridicalUser);
    }

    @Override
    @PutMapping(path = "/juridicalUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody JuridicalUser juridicalUser) {
        return super.put(juridicalUser);
    }

}
