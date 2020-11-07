package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.*;
import vilnius.tech.session.HibernateController;

import java.time.ZonedDateTime;

public class ExpenseController extends HibernateController<Expense> {

    public ExpenseController(Session session) {
        super(Expense.class, session);
    }

    public Expense create(User owner, long sum, ZonedDateTime dateTime, FinancialCategory category, ExpenseType type) {
        var expense = new Expense();
        expense.setOwner(owner);
        expense.setSum(sum);
        expense.setDateTime(dateTime);
        expense.setCategory(category);
        expense.setType(type);
        return update(expense);
    }

}
