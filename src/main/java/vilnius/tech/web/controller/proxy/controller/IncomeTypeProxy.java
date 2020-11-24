package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.IncomeType;
import vilnius.tech.hibernate.service.IncomeTypeService;
import vilnius.tech.web.utils.HibernateUtils;
import vilnius.tech.web.utils.JsonResponseUtils;
import vilnius.tech.web.utils.Messages;

public class IncomeTypeProxy extends AbstractControllerProxy<IncomeType, IncomeTypeService> {
    @Override
    public ResponseEntity<String> post(IncomeType incomeType) {
        if(post_Invalid(incomeType))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        return JsonResponseUtils.OK(createService().create(
                incomeType.getName(),
                incomeType.getCode()
        ));
    }

    @Override
    public ResponseEntity<String> put(IncomeType incomeType) {
        if(put_Invalid(incomeType))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var service = createService();

        var databaseIncomeType = service.find(incomeType.getId());
        if(databaseIncomeType == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), incomeType.getId()));

        if(namePresent(incomeType))
            databaseIncomeType.setName(incomeType.getName());

        if(codePresent(incomeType))
            databaseIncomeType.setCode(incomeType.getCode());

        return JsonResponseUtils.OK(service.update(databaseIncomeType));
    }

    private boolean post_Invalid(IncomeType incomeType) {
        return !namePresent(incomeType) || !codePresent(incomeType);
    }

    private boolean put_Invalid(IncomeType incomeType) {
        if(incomeType.getId() == null)
            return true;

        return !namePresent(incomeType) && !codePresent(incomeType);
    }

    private boolean namePresent(IncomeType incomeType) {
        return incomeType.getName() != null && !incomeType.getName().isBlank();
    }

    private boolean codePresent(IncomeType incomeType) {
        return incomeType.getCode() != null && !incomeType.getCode().isBlank();
    }

    @Override
    protected IncomeTypeService createService() {
        return new IncomeTypeService(HibernateUtils.getSession());
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    public final static String ENTITY_NAME = "Income Type";
}
