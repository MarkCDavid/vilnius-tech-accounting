package vilnius.tech.controller.crud;

import vilnius.tech.dal.Address;
import vilnius.tech.dal.City;
import vilnius.tech.dal.IncomeType;
import vilnius.tech.session.Session;

public class IncomeTypeCU {

    public static IncomeType create(Session session, String name, String code) {
        var incomeType = new IncomeType(session);
        update(incomeType, name, code);
        return incomeType;
    }

    public static void update(IncomeType incomeType, String name, String code) {
        incomeType.setName(name);
        incomeType.setCode(code);
    }

    private IncomeTypeCU() { }
}
