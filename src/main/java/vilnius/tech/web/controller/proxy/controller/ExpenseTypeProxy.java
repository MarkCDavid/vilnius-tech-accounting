package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.ExpenseType;
import vilnius.tech.hibernate.service.ExpenseTypeService;
import vilnius.tech.web.controller.utils.HibernateUtils;
import vilnius.tech.web.controller.utils.JsonResponseUtils;
import vilnius.tech.web.controller.utils.Messages;

public class ExpenseTypeProxy extends AbstractControllerProxy<ExpenseType, ExpenseTypeService> {
    @Override
    public ResponseEntity<String> post(ExpenseType expenseType) {
        if(post_Invalid(expenseType))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        return JsonResponseUtils.OK(createService().create(
                expenseType.getName(),
                expenseType.getCode()
        ));
    }

    @Override
    public ResponseEntity<String> put(ExpenseType expenseType) {
        if(put_Invalid(expenseType))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var service = createService();

        var databaseExpenseType = service.find(expenseType.getId());
        if(databaseExpenseType == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), expenseType.getId()));

        databaseExpenseType.setName(expenseType.getName());
        databaseExpenseType.setCode(expenseType.getCode());

        return JsonResponseUtils.OK(service.update(databaseExpenseType));
    }

    private boolean post_Invalid(ExpenseType expenseType) {
        return expenseType.getName() == null || expenseType.getName().isBlank() ||
                expenseType.getCode() == null || expenseType.getCode().isBlank();
    }

    private boolean put_Invalid(ExpenseType expenseType) {
        return expenseType.getId() == null ||
                expenseType.getName() == null || expenseType.getName().isBlank() ||
                expenseType.getCode() == null ||  expenseType.getCode().isBlank();
    }

    @Override
    protected ExpenseTypeService createService() {
        return new ExpenseTypeService(HibernateUtils.getSession());
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    public final static String ENTITY_NAME = "Expense Type";
}
