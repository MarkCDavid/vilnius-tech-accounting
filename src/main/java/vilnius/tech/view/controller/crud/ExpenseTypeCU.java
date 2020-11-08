package vilnius.tech.view.controller.crud;

import vilnius.tech.dal.ExpenseType;
import vilnius.tech.session.Session;

public class ExpenseTypeCU {

    public static ExpenseType create(Session session, String name, String code) {
        var expenseType = new ExpenseType(session);
        update(expenseType, name, code);
        return expenseType;
    }

    public static void update(ExpenseType expenseType, String name, String code) {
        expenseType.setName(name);
        expenseType.setCode(code);
    }

    private ExpenseTypeCU() { }
}
