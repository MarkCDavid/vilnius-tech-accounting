package vilnius.tech.seeds;

import vilnius.tech.dal.*;
import vilnius.tech.session.Session;

import java.util.Objects;

public class CategorySeeder implements Seeder {


    @Override
    public void seed(Session session) {
        new UserSeeder().seed(session);
        var user = session.query(User.class, u -> Objects.equals(u.getUsername(), "administrator")).get(0);

        var vehicles = createCategory(session, "Vehicles", user);
        createVehicleCategories(session, vehicles, user, "AAA-000");
        createVehicleCategories(session, vehicles, user, "BBB-001");
        createVehicleCategories(session, vehicles, user, "AAA-002");
        createVehicleCategories(session, vehicles, user, "AEF-023");

        var food = createCategory(session, "Food", user);
        createCategory(session, "Delivery", user, food);
        createCategory(session, "Eating Out", user, food);

        var officeExpenses = createCategory(session, "Office expenses", user);
        createCategory(session, "Paper", user, officeExpenses);
        createCategory(session, "Electricity", user, officeExpenses);
    }

    private void createVehicleCategories(Session session, FinancialCategory parent, User owner, String name) {
        var vehicle = createCategory(session, name, owner, parent);
        createCategory(session, "Petrol", owner, vehicle);
        createCategory(session, "Wash", owner, vehicle);
        createCategory(session, "Repairs", owner, vehicle);
    }

    private FinancialCategory createCategory(Session session, String name, User user, FinancialCategory parent) {
        FinancialCategory category = new FinancialCategory(session);
        category.setName(name);
        category.setOwner(user);
        category.setParent(parent);
        return category;
    }

    private FinancialCategory createCategory(Session session, String name, User user) {
        return createCategory(session, name, user, null);
    }
}
