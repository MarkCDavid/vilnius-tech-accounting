package vilnius.tech.seeds;

import vilnius.tech.dal.IncomeType;
import vilnius.tech.session.Session;

public class IncomeTypeSeeder implements Seeder {

    @Override
    public void seed(Session session) {
        createType(session, "Salary", "SAL");
        createType(session, "Dividends", "DIV");
        createType(session, "Rent", "RNT");
        createType(session, "Sales", "SLS");
    }



    private IncomeType createType(Session session, String name, String code) {
        IncomeType type = new IncomeType(session);
        type.setName(name);
        type.setCode(code);
        return type;
    }
}
