package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.error.router.JsonMessageRouter;
import vilnius.tech.hibernate.ContactInformation;
import vilnius.tech.web.controller.proxy.controller.ContactInformationProxy;

@RestController
public class ContactInformationController extends WebApiCRUDController<ContactInformation, ContactInformationProxy> {

    protected ContactInformationController() {
        super(new ContactInformationProxy(), new JsonMessageRouter());
    }

    @Override
    @GetMapping(path = "/contactInformation/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        return super.getAll(take, skip);
    }

    @Override
    @GetMapping(path = "/contactInformation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(@PathVariable(name="id") Integer id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(path = "/contactInformation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
        return super.delete(id);
    }


    @Override
    @PostMapping(path = "/contactInformation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody ContactInformation contactInformation) {
        return super.post(contactInformation);
    }

    @Override
    @PutMapping(path = "/contactInformation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@RequestBody ContactInformation contactInformation) {
        return super.put(contactInformation);
    }

}
