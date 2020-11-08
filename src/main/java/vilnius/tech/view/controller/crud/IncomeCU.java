package vilnius.tech.view.controller.crud;

import vilnius.tech.dal.*;
import vilnius.tech.session.Session;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class IncomeCU {

    public static Income create(Session session, IncomeType incomeType, User owner, long sum) {
        var income = new Income(session);
        update(income, incomeType, owner, sum);
        return income;
    }

    public static void update(Income income, IncomeType incomeType, User owner, long sum) {
        income.setType(incomeType);
        income.setOwner(owner);
        income.setSum(sum);
        income.setDateTime(ZonedDateTime.now(ZoneId.systemDefault()));
    }

    private IncomeCU() { }
}
