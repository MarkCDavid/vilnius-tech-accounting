package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.service.AddressService;
import vilnius.tech.web.controller.utils.GsonUtils;
import vilnius.tech.web.controller.utils.HibernateUtils;
import vilnius.tech.web.controller.utils.JsonResponseUtils;

@RestController
public class AddressController {

    @GetMapping(path = "/address/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        var session = HibernateUtils.getSession();
        var service = new AddressService(session);
        return JsonResponseUtils.OK(service.find(take, skip));
    }

    @GetMapping(path = "/address/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(
            @PathVariable Integer id
    ) {
        var session = HibernateUtils.getSession();
        var service = new AddressService(session);
        return JsonResponseUtils.OK(service.find(id));
    }


}


//
// GET/ALL
// GET/ID
// GET/{SOME_PARAM}
// POST
// PUT/ID
// DELETE