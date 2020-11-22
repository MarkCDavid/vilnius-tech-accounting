package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vilnius.tech.hibernate.service.ExpenseService;
import vilnius.tech.hibernate.service.IncomeService;
import vilnius.tech.web.controller.utils.GsonUtils;
import vilnius.tech.web.controller.utils.HibernateUtils;

@RestController
public class IncomeController {

    @GetMapping(path = "/income/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public String get(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        var session = HibernateUtils.getSession();
        var service = new IncomeService(session);
        return GsonUtils.getGson().toJson(service.find(take, skip));
    }
}
