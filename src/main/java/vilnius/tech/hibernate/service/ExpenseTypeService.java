package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.*;

public class ExpenseTypeService extends HibernateService<ExpenseType> {

    public ExpenseTypeService(Session session) {
        super(ExpenseType.class, session);
    }

    public ExpenseType create(String name, String code) {
        return update(new ExpenseType(), name, code);
    }

    public ExpenseType update(ExpenseType expenseType, String name, String code) {
        expenseType.setName(name);
        expenseType.setCode(code);
        return update(expenseType);
    }
}
