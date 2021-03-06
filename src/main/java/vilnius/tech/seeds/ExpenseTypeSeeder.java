package vilnius.tech.seeds;

import org.hibernate.Session;
import vilnius.tech.hibernate.ExpenseType;
import vilnius.tech.hibernate.service.ExpenseTypeService;

public class ExpenseTypeSeeder implements Seeder {

    @Override
    public void seed(Session session) {
        createType(session, "Food", "FUD");
        createType(session, "Petrol", "PET");
        createType(session, "Paper", "PAP");
        createType(session, "Cleaning", "CLN");
    }

    private ExpenseType createType(Session session, String name, String code) {
        var controller = new ExpenseTypeService(session);
        return controller.create(name, code);
    }
}
