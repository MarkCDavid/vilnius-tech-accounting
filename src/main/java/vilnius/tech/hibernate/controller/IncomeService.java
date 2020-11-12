package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.*;

import java.time.ZonedDateTime;

public class IncomeService extends HibernateService<Income> {

    public IncomeService(Session session) {
        super(Income.class, session);
    }

    public Income create(User owner, long sum, ZonedDateTime dateTime, FinancialCategory category, IncomeType type) {
        return update(new Income(), owner, sum, dateTime, category, type);
    }


    public Income update(Income income, User owner, long sum, ZonedDateTime dateTime, FinancialCategory category, IncomeType type) {
        income.setOwner(owner);
        income.setSum(sum);
        income.setDateTime(dateTime);
        income.setCategory(category);
        income.setType(type);
        return update(income);
    }

}
