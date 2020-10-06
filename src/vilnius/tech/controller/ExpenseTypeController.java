package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class ExpenseTypeController extends CRUDManager<ExpenseType> implements CRUD<ExpenseType> {

    public ExpenseTypeController(Session session) {
        super(session);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected void initializeReferenceMap(Map<String, Reference> referenceMap) {
        referenceMap.put("expenses", new Reference(Expense.class, (Function<Expense, Integer>) expense -> expense.getType().getOid()));
    }

    @Override
    public ExpenseType create(Scanner scanner) {
        String name = UserInput.getString(scanner, "Expense name");
        String code = UserInput.getString(scanner, "Expense code");

        ExpenseType expense = new ExpenseType(getSession());

        expense.setName(name);
        expense.setCode(code);
        return expense;
    }

    @Override
    public List<ExpenseType> readAll() {
        return getSession().get(ExpenseType.class);
    }

    @Override
    public ExpenseType update(Scanner scanner) {
        ExpenseType expenseType = read(scanner);

        if(UserInput.getUserConfirmation(scanner, "Update Expense name")) {
            expenseType.setName(UserInput.getString(scanner, "Expense name"));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Expense code")) {
            expenseType.setCode(UserInput.getString(scanner, "Expense code"));
        }

        return expenseType;
    }

    @Override
    public void delete(Scanner scanner) {
        ExpenseType expenseType = read(scanner);
        if(expenseType == null || expenseType.isDeleted())
            return;

        List<Expense> eRefs = getSession().query(Expense.class, e -> e.getType().getOid() == expenseType.getOid());
        if(!eRefs.isEmpty() && !UserInput.getDeleteConfirmation(scanner, getManagedObjectName(), "Expense", eRefs.size())) {
            return;
        }

        for(Expense e: eRefs) {
            e.setType(null);
        }

        expenseType.setDeleted(true);
    }


    @Override
    protected String getManagedObjectName() {
        return "Expense Type";
    }
}
