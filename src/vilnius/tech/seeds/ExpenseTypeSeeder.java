package vilnius.tech.seeds;

import vilnius.tech.dal.ExpenseType;
import vilnius.tech.session.Session;

public class ExpenseTypeSeeder implements Seeder {

    @Override
    public void seed(Session session) {
        createType(session, "Food", "FUD");
        createType(session, "Petrol", "PET");
        createType(session, "Paper", "PAP");
        createType(session, "Cleaning", "CLN");
    }

    private ExpenseType createType(Session session, String name, String code) {
        ExpenseType type = new ExpenseType(session);
        type.setName(name);
        type.setCode(code);
        return type;
    }
}
