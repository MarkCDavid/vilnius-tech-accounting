package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.error.router.JsonMessageRouter;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.web.controller.proxy.controller.PhysicalUserProxy;

@RestController
public class PhysicalUserController extends WebApiCRUDController<PhysicalUser, PhysicalUserProxy> {

    protected PhysicalUserController() {
        super(new PhysicalUserProxy(), new JsonMessageRouter());
    }

    @Override
    @GetMapping(path = "/physicalUser/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        return super.getAll(take, skip);
    }

    @Override
    @GetMapping(path = "/physicalUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable(name="id") Integer id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(path = "/physicalUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        return super.delete(id);
    }

    @Override
    @PostMapping(path = "/physicalUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody PhysicalUser physicalUser) {
        return super.post(physicalUser);
    }

    @Override
    @PutMapping(path = "/physicalUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody PhysicalUser physicalUser) {
        return super.put(physicalUser);
    }

}
