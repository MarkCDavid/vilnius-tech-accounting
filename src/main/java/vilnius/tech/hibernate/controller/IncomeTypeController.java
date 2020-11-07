package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.IncomeType;
import vilnius.tech.session.HibernateController;

public class IncomeTypeController extends HibernateController<IncomeType> {

    public IncomeTypeController(Session session) {
        super(IncomeType.class, session);
    }

    public IncomeType create(String name, String code) {
        var incomeType = new IncomeType();
        incomeType.setName(name);
        incomeType.setCode(code);
        return update(incomeType);
    }

}
