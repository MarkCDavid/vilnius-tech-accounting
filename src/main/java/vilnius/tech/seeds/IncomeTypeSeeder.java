package vilnius.tech.seeds;

import org.hibernate.Session;
import vilnius.tech.hibernate.IncomeType;
import vilnius.tech.hibernate.controller.IncomeTypeService;

public class IncomeTypeSeeder implements Seeder {

    @Override
    public void seed(Session session) {
        createType(session, "Salary", "SAL");
        createType(session, "Dividends", "DIV");
        createType(session, "Rent", "RNT");
        createType(session, "Sales", "SLS");
    }

    private IncomeType createType(Session session, String name, String code) {
        var controller = new IncomeTypeService(session);
        return controller.create(name, code);
    }
}
