package vilnius.tech.view.controller.crud;

import vilnius.tech.dal.FinancialCategory;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;

public class FinancialCategoryCU {

    public static FinancialCategory create(Session session, FinancialCategory parent, String name, User owner) {
        var financialCategory = new FinancialCategory(session);
        update(financialCategory, parent, name, owner);
        return financialCategory;
    }

    public static void update(FinancialCategory financialCategory, FinancialCategory parent, String name, User owner) {
        financialCategory.setParent(parent);
        financialCategory.setName(name);
        financialCategory.setOwner(owner);
    }

    private FinancialCategoryCU() { }
}
