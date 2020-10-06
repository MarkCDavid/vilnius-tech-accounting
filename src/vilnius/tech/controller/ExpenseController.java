package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.dal.Expense;
import vilnius.tech.utils.UserInput;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class ExpenseController extends CRUDManager<Expense> implements CRUD<Expense> {



    public ExpenseController(Session session, FinancialCategory parent) {
        super(session);
        this.parent = parent;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected void initializeReferenceMap(Map<String, Reference> referenceMap) {
        referenceMap.put("expenses", new Reference(Expense.class, (Function<Expense, Integer>) expense -> expense.getType().getOid()));
    }

    @Override
    public Expense create(Scanner scanner) {
        ExpenseType type = new ExpenseTypeController(getSession()).read(scanner);
        User owner = new UserController(getSession()).read(scanner);
        int sum = UserInput.getInteger(scanner, "Expense").orElse(0);
        ZonedDateTime dateTime = UserInput.getDate(scanner, "Date (empty for current time)");

        Expense expense = new Expense(getSession());
        parent.getExpenses().add(expense);
        expense.setType(type);
        expense.setOwner(owner);
        expense.setSum(sum);
        expense.setDateTime(dateTime);
        return expense;
    }

    @Override
    public List<Expense> readAll() {
        return getSession().get(Expense.class);
    }

    @Override
    public Expense update(Scanner scanner) {
        Expense expense = read(scanner);

        if(UserInput.getUserConfirmation(scanner, "Update Expense Type")) {
            expense.setType( new ExpenseTypeController(getSession()).read(scanner));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Owner")) {
            expense.setOwner(new UserController(getSession()).read(scanner));
        }

        if(UserInput.getUserConfirmation(scanner, "Update sum")) {
            expense.setSum(UserInput.getInteger(scanner, "Expense").orElse(0));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Owner")) {
            expense.setDateTime(UserInput.getDate(scanner, "Date (empty for current time)"));
        }

        return expense;
    }

    @Override
    public void delete(Scanner scanner) {
        Expense expense = read(scanner);
        if(expense == null || expense.isDeleted())
            return;

        parent.getExpenses().removeIf(e -> e.getOid() == expense.getOid());
        expense.setDeleted(true);
    }


    @Override
    protected String getManagedObjectName() {
        return "Expense";
    }
    private final FinancialCategory parent;
}
