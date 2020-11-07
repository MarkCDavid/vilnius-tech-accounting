package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.User;
import vilnius.tech.session.HibernateController;

public class FinancialCategoryController extends HibernateController<FinancialCategory> {

    public FinancialCategoryController(Session session) {
        super(FinancialCategory.class, session);
    }

    public FinancialCategory create(FinancialCategory parent, String name, User owner) {
        var financialCategory = new FinancialCategory();
        financialCategory.setParent(parent);
        financialCategory.setName(name);
        financialCategory.setOwner(owner);
        return update(financialCategory);
    }

}
