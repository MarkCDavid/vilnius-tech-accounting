package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.*;
import vilnius.tech.session.HibernateController;

import java.time.ZonedDateTime;

public class ExpenseTypeController extends HibernateController<ExpenseType> {

    public ExpenseTypeController(Session session) {
        super(ExpenseType.class, session);
    }

    public ExpenseType create(String name, String code) {
        var expenseType = new ExpenseType();
        expenseType.setName(name);
        expenseType.setCode(code);
        return update(expenseType);
    }

}
