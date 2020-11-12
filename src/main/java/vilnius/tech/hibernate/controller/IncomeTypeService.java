package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.IncomeType;

public class IncomeTypeService extends HibernateService<IncomeType> {

    public IncomeTypeService(Session session) {
        super(IncomeType.class, session);
    }

    public IncomeType create(String name, String code) {
        return update(new IncomeType(), name, code);
    }

    public IncomeType update(IncomeType incomeType, String name, String code) {
        incomeType.setName(name);
        incomeType.setCode(code);
        return update(incomeType);
    }

}
