package vilnius.tech.seeds;

import org.hibernate.Session;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.service.FinancialCategoryService;
import vilnius.tech.hibernate.service.UserService;

public class CategorySeeder implements Seeder {

    @Override
    public void seed(Session session) {
        new UserSeeder().seed(session);

        var userController = new UserService(session);

        var administrator = userController.find_Username("admin");
        var aurimas = userController.find_Username("Aurimas");

        var vehicles = createCategory(session, "Vehicles", administrator);
        createVehicleCategories(session, vehicles, administrator, "AAA-000");
        createVehicleCategories(session, vehicles, administrator, "BBB-001");
        createVehicleCategories(session, vehicles, administrator, "AAA-002");
        createVehicleCategories(session, vehicles, administrator, "AEF-023");

        var food = createCategory(session, "Food", administrator);
        createCategory(session, "Delivery", aurimas, food);
        createCategory(session, "Eating Out", aurimas, food);

        var officeExpenses = createCategory(session, "Office expenses", administrator);
        createCategory(session, "Paper", administrator, officeExpenses);
        createCategory(session, "Electricity", aurimas, officeExpenses);
    }

    private void createVehicleCategories(Session session, FinancialCategory parent, User owner, String name) {
        var vehicle = createCategory(session, name, owner, parent);
        createCategory(session, "Petrol", owner, vehicle);
        createCategory(session, "Wash", owner, vehicle);
        createCategory(session, "Repairs", owner, vehicle);
    }

    private FinancialCategory createCategory(Session session, String name, User user, FinancialCategory parent) {
        var controller = new FinancialCategoryService(session);
        return controller.create(parent, name, user);
    }

    private FinancialCategory createCategory(Session session, String name, User user) {
        return createCategory(session, name, user, null);
    }
}
