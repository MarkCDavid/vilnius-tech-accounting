package vilnius.tech.web.controller.proxy.controller;

import org.springframework.http.ResponseEntity;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.Expense;
import vilnius.tech.hibernate.ExpenseType;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.service.FinancialCategoryService;
import vilnius.tech.hibernate.service.ExpenseService;
import vilnius.tech.hibernate.service.ExpenseTypeService;
import vilnius.tech.hibernate.service.UserService;
import vilnius.tech.utils.TimeUtils;
import vilnius.tech.web.utils.HibernateUtils;
import vilnius.tech.web.utils.JsonResponseUtils;
import vilnius.tech.web.utils.Messages;

public class ExpenseProxy extends AbstractControllerProxy<Expense, ExpenseService> {
    @Override
    public ResponseEntity<String> post(Expense expense) {
        if(post_Invalid(expense))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        var owner = getOwner(expense);
        if(owner == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound_Field(getEntityName(), "username", expense.getOwner().getUsername()));

        var expenseType = getExpenseType(expense);
        if(expenseType == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound_Field(getEntityName(), "code", expense.getType().getCode()));

        var financialCategory = getFinancialCategory(expense);
        if(financialCategory == null)
            return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), expense.getCategory().getId()));

        try(var service = createService()) {
            return JsonResponseUtils.OK(service.create(
                    owner,
                    expense.getSum(),
                    TimeUtils.now(),
                    financialCategory,
                    expenseType
            ));
        }
    }

    @Override
    public ResponseEntity<String> put(Expense expense) {
        if(put_Invalid(expense))
            return JsonResponseUtils.BAD(Messages.invalidData(getEntityName()));

        try(var service = createService()) {

            var databaseExpense = service.find(expense.getId());
            if (databaseExpense == null)
                return JsonResponseUtils.BAD(Messages.itemNotFound(getEntityName(), expense.getId()));

            if (sumPresent(expense)) {
                databaseExpense.setSum(expense.getSum());
            }

            if (expenseTypePresent(expense)) {
                var expenseType = getExpenseType(expense);
                if (expenseType == null)
                    return JsonResponseUtils.BAD(Messages.itemNotFound_Field(getEntityName(), "code", expense.getType().getCode()));
                databaseExpense.setType(expenseType);
            }

            return JsonResponseUtils.OK(service.update(databaseExpense));
        }
    }

    public ResponseEntity<String> get_Category(Integer id) {
        try(var service = createService();
            var categoryService = new FinancialCategoryService(HibernateUtils.getSession())) {
            var category = categoryService.find(id);

            if (category == null)
                return JsonResponseUtils.BAD(Messages.itemNotFound(FinancialCategoryProxy.ENTITY_NAME, id));

            return JsonResponseUtils.OK(service.find_Category(category));
        }
    }


    private boolean post_Invalid(Expense expense) {
        return !expenseTypePresent(expense) ||
               !financialCategoryPresent(expense) ||
               !ownerPresent(expense) ||
               !sumPresent(expense);

    }

    private boolean put_Invalid(Expense expense) {
        if(expense.getId() == null)
            return true;

        return !expenseTypePresent(expense) &&
               !sumPresent(expense);
    }

    private boolean expenseTypePresent(Expense expense) {
        return expense.getType() != null &&
                expense.getType().getCode() != null &&
                !expense.getType().getCode().isBlank();
    }

    private boolean ownerPresent(Expense expense) {
        return expense.getOwner() != null &&
                expense.getOwner().getUsername() != null &&
                !expense.getOwner().getUsername().isBlank();
    }

    private boolean financialCategoryPresent(Expense expense) {
        return expense.getCategory() != null &&
                expense.getCategory().getId() != null;
    }

    private boolean sumPresent(Expense expense) {
        return expense.getSum() != null;
    }


    private User getOwner(Expense expense) {
        try(var userService = new UserService(HibernateUtils.getSession())) {

            if (!ownerPresent(expense))
                return null;

            return userService.find_Username(expense.getOwner().getUsername());
        }
    }

    private FinancialCategory getFinancialCategory(Expense expense) {
        try(var financialCategoryService = new FinancialCategoryService(HibernateUtils.getSession())) {

            if (!financialCategoryPresent(expense))
                return null;

            return financialCategoryService.find(expense.getCategory().getId());
        }
    }

    private ExpenseType getExpenseType(Expense expense) {
        try(var typeService = new ExpenseTypeService(HibernateUtils.getSession())) {

            if (!expenseTypePresent(expense))
                return null;

            return typeService.find_Code(expense.getType().getCode());
        }
    }

    @Override
    protected ExpenseService createService() {
        return new ExpenseService(HibernateUtils.getSession());
    }

    @Override
    protected String getEntityName() {
        return ENTITY_NAME;
    }

    public final static String ENTITY_NAME = "Expense";
}
