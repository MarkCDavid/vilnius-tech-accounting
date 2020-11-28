package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.*;
import vilnius.tech.hibernate.Income;
import vilnius.tech.hibernate.service.*;
import vilnius.tech.hibernate.service.IncomeService;
import vilnius.tech.utils.TimeUtils;
import vilnius.tech.web.utils.HibernateUtils;
import vilnius.tech.web.utils.JsonResponseUtils;
import vilnius.tech.web.utils.Messages;

public class IncomeProxy extends AbstractControllerProxy<Income, IncomeService> {
    @Override
    public ResponseEntity<String> post(Income income) {
        if(post_Invalid(income))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var owner = getOwner(income);
        if(owner == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound_Field(getEntityName(), "username", income.getOwner().getUsername()));

        var incomeType = getIncomeType(income);
        if(incomeType == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound_Field(getEntityName(), "code", income.getType().getCode()));

        var financialCategory = getFinancialCategory(income);
        if(financialCategory == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), income.getCategory().getId()));
        try(var service = createService()) {
            return JsonResponseUtils.OK(service.create(
                    owner,
                    income.getSum(),
                    TimeUtils.now(),
                    financialCategory,
                    incomeType
            ));
        }
    }

    @Override
    public ResponseEntity<String> put(Income income) {
        if(put_Invalid(income))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));
        try(var service = createService()) {

            var databaseIncome = service.find(income.getId());
            if (databaseIncome == null)
                return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), income.getId()));

            if (sumPresent(income)) {
                databaseIncome.setSum(income.getSum());
            }

            if (incomeTypePresent(income)) {
                var incomeType = getIncomeType(income);
                if (incomeType == null)
                    return JsonResponseUtils.BAD(Messages.itemNotFound_Field(getEntityName(), "code", income.getType().getCode()));
                databaseIncome.setType(incomeType);
            }

            return JsonResponseUtils.OK(service.update(databaseIncome));
        }
    }

    public ResponseEntity<String> get_Category(Integer id) {

        try(var categoryService = new FinancialCategoryService(HibernateUtils.getSession())) {
            var category = categoryService.find(id);

            if (category == null)
                return JsonResponseUtils.BAD(Messages.itemNotFound(FinancialCategoryProxy.ENTITY_NAME, id));

            return JsonResponseUtils.OK(createService().find_Category(category));
        }
    }

    private boolean post_Invalid(Income income) {
        return !incomeTypePresent(income) ||
               !financialCategoryPresent(income) ||
               !ownerPresent(income) ||
               !sumPresent(income);

    }

    private boolean put_Invalid(Income income) {
        if(income.getId() == null)
            return true;

        return !incomeTypePresent(income) &&
               !sumPresent(income);
    }

    private boolean incomeTypePresent(Income income) {
        return income.getType() != null &&
                income.getType().getCode() != null &&
                !income.getType().getCode().isBlank();
    }

    private boolean ownerPresent(Income income) {
        return income.getOwner() != null &&
                income.getOwner().getUsername() != null &&
                !income.getOwner().getUsername().isBlank();
    }

    private boolean financialCategoryPresent(Income income) {
        return income.getCategory() != null &&
                income.getCategory().getId() != null;
    }

    private boolean sumPresent(Income income) {
        return income.getSum() != null;
    }


    private User getOwner(Income income) {
        try(var userService = new UserService(HibernateUtils.getSession())) {

            if (!ownerPresent(income))
                return null;

            return userService.find_Username(income.getOwner().getUsername());
        }
    }

    private FinancialCategory getFinancialCategory(Income income) {
        try(var financialCategoryService = new FinancialCategoryService(HibernateUtils.getSession())) {

            if (!financialCategoryPresent(income))
                return null;

            return financialCategoryService.find(income.getCategory().getId());
        }
    }

    private IncomeType getIncomeType(Income income) {
        try(var typeService = new IncomeTypeService(HibernateUtils.getSession())) {

            if (!incomeTypePresent(income))
                return null;

            return typeService.find_Code(income.getType().getCode());
        }
    }

    @Override
    protected IncomeService createService() {
        return new IncomeService(HibernateUtils.getSession());
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    public final static String ENTITY_NAME = "Income";
}
