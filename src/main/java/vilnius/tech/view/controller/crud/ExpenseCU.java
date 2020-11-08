package vilnius.tech.view.controller.crud;

import vilnius.tech.dal.Expense;
import vilnius.tech.dal.ExpenseType;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ExpenseCU {

    public static Expense create(Session session, ExpenseType expenseType, User owner, long sum) {
        var expense = new Expense(session);
        update(expense, expenseType, owner, sum);
        return expense;
    }

    public static void update(Expense expense, ExpenseType expenseType, User owner, long sum) {
        expense.setType(expenseType);
        expense.setOwner(owner);
        expense.setSum(sum);
        expense.setDateTime(ZonedDateTime.now(ZoneId.systemDefault()));
    }

    private ExpenseCU() { }
}
