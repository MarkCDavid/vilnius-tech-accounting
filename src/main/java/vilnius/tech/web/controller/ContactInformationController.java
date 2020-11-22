package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.hibernate.service.ContactInformationService;
import vilnius.tech.web.controller.utils.GsonUtils;
import vilnius.tech.web.controller.utils.HibernateUtils;

@RestController
public class ContactInformationController {

    @GetMapping(path = "/contactInformation/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public String get(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        var session = HibernateUtils.getSession();
        var service = new ContactInformationService(session);
        return GsonUtils.getGson().toJson(service.find(take, skip));
    }
}
