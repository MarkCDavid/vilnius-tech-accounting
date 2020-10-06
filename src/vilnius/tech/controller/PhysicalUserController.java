package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.UserInput;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class PhysicalUserController extends CRUDManager<PhysicalUser> {

    public PhysicalUserController(Session session) {
        super(session);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected void initializeReferenceMap(Map<String, Reference> referenceMap) {
        referenceMap.put("financial categories - owner", new Reference(FinancialCategory.class, (Function<FinancialCategory, Integer>) fc -> fc.getOwner().getOid()));
        referenceMap.put("expenses", new Reference(Expense.class, (Function<Expense, Integer>) e -> e.getOwner().getOid()));
        referenceMap.put("incomes", new Reference(Income.class, (Function<Income, Integer>) i -> i.getOwner().getOid()));
    }

    @Override
    public PhysicalUser create(Scanner scanner) {
        String username = UserInput.getString(scanner, "Username");
        String password = UserInput.getString(scanner, "Password");
        String name = UserInput.getString(scanner, "Name");
        String surname = UserInput.getString(scanner, "Surname");

        System.out.println("Contact Information:");
        ContactInformation contactInformation = new ContactInformationController(getSession()).read(scanner, true);
        PhysicalUser physicalUser = new PhysicalUser(getSession());

        physicalUser.setUsername(username);
        physicalUser.setPassword(password);
        physicalUser.setName(name);
        physicalUser.setSurname(surname);
        physicalUser.setContactInformation(contactInformation);


        return physicalUser;
    }

    @Override
    public List<PhysicalUser> readAll() {
        return getSession().get(PhysicalUser.class);
    }

    @Override
    public PhysicalUser update(Scanner scanner) {
        return null;
    }

    @Override
    public void delete(Scanner scanner) {

    }

    private static final String OBJECT_NAME = "Physical User";

    @Override
    protected String getManagedObjectName() {
        return OBJECT_NAME;
    }
}
