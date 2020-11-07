package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.*;
import vilnius.tech.session.HibernateController;

import java.time.ZonedDateTime;

public class IncomeController extends HibernateController<Income> {

    public IncomeController(Session session) {
        super(Income.class, session);
    }

    public Income create(User owner, long sum, ZonedDateTime dateTime, FinancialCategory category, IncomeType type) {
        var income = new Income();
        income.setOwner(owner);
        income.setSum(sum);
        income.setDateTime(dateTime);
        income.setCategory(category);
        income.setType(type);
        return update(income);
    }

}
