package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.*;

import java.time.ZonedDateTime;

public class ExpenseService extends HibernateService<Expense> {

    public ExpenseService(Session session) {
        super(Expense.class, session);
    }

    public Expense create(User owner, long sum, ZonedDateTime dateTime, FinancialCategory category, ExpenseType type) {
        return update(new Expense(), owner, sum, dateTime, category, type);
    }

    public Expense update(Expense expense, User owner, long sum, ZonedDateTime dateTime, FinancialCategory category, ExpenseType type) {
        expense.setOwner(owner);
        expense.setSum(sum);
        expense.setDateTime(dateTime);
        expense.setCategory(category);
        expense.setType(type);
        return update(expense);
    }

}
