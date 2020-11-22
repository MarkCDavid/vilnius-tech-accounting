package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.hibernate.City;
import vilnius.tech.hibernate.service.CityService;
import vilnius.tech.web.controller.utils.GsonUtils;
import vilnius.tech.web.controller.utils.HibernateUtils;
import vilnius.tech.web.controller.utils.JsonResponseUtils;
import vilnius.tech.web.controller.utils.Message;

@RestController
public class CityController {

    @GetMapping(path = "/city/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        var session = HibernateUtils.getSession();
        var service = new CityService(session);

        return JsonResponseUtils.OK(service.find(take, skip));
    }

    @GetMapping(path = "/city/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public String get(@PathVariable(name = "id") Integer id) {
        var session = HibernateUtils.getSession();
        var service = new CityService(session);
        return GsonUtils.getGson().toJson(service.find(id));
    }



    @PostMapping(path = "/city", produces= MediaType.APPLICATION_JSON_VALUE)
    public String get(@RequestBody City city) {
        var session = HibernateUtils.getSession();

        var service = new CityService(session);
        return GsonUtils.getGson().toJson(service.find());
    }

}
