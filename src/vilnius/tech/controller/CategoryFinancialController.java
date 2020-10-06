package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.UserInput;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

public class CategoryFinancialController extends CRUDManager<FinancialCategory> implements CRUD<FinancialCategory> {

    public CategoryFinancialController(Session session, FinancialCategory parent) {
        super(session);
        this.parent = parent;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected void initializeReferenceMap(Map<String, Reference> referenceMap) {
        referenceMap.put("expenses", new Reference(Expense.class, (Function<Expense, Integer>) expense -> expense.getType().getOid()));
    }

    private static final String MANAGE_RESPONSIBLE = "manage responsible";
    private static final String MANAGE_SUBCATEGORY = "manage subcategories";
    private static final String MANAGE_CATEGORY = "manage categories";
    private static final String MANAGE_INCOME = "manage income";
    private static final String MANAGE_EXPENSES = "manage expenses";

    private static String getCategoryManagementCommand(FinancialCategory parent) {
        return parent == null ? MANAGE_CATEGORY : MANAGE_SUBCATEGORY;
    }

    private boolean canManage() {
        return parent != null;
    }


    @Override
    protected void showOptions(Scanner scanner) {
        super.showOptions(scanner);
        if(canManage())
                System.out.printf("%s%n", MANAGE_RESPONSIBLE);

        System.out.printf("%s%n", getCategoryManagementCommand(parent));

        if(canManage())
            System.out.printf("%s%n", MANAGE_INCOME);

        if(canManage())
            System.out.printf("%s%n", MANAGE_EXPENSES);
    }

    @Override
    protected void matchOptions(Scanner scanner, String userInput) {
        super.matchOptions(scanner, userInput);
        if(canManage() && Objects.equals(userInput, MANAGE_RESPONSIBLE)) {
            new ResponsibleController(getSession(), parent.getResponsibleUsers()).read(scanner);
        }
        else if(Objects.equals(userInput, Objects.equals(userInput, getCategoryManagementCommand(parent)))) {
            FinancialCategory category = read(scanner);
            new CategoryFinancialController(getSession(), category).manage(scanner);
        }
        else if(canManage() && Objects.equals(userInput, MANAGE_INCOME)) {
            new IncomeController(getSession(), parent).manage(scanner);
        }
        else if(canManage() && Objects.equals(userInput, MANAGE_EXPENSES)) {
            new ExpenseController(getSession(), parent).manage(scanner);
        }
    }

    @Override
    public FinancialCategory create(Scanner scanner) {
        String name = UserInput.getString(scanner, "Category Name");
        User owner = new UserController(getSession()).read(scanner);

        FinancialCategory category = new FinancialCategory(getSession());
        category.setName(name);
        category.setOwner(owner);
        return category;
    }

    @Override
    public List<FinancialCategory> readAll() {
        return getSession().query(FinancialCategory.class, fc -> fc.getParent() == parent);
    }

    @Override
    public FinancialCategory update(Scanner scanner) {
        FinancialCategory category = read(scanner);

        if(UserInput.getUserConfirmation(scanner, "Update Category Name")) {
            category.setName(UserInput.getString(scanner, "Category Name"));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Owner")) {
            category.setOwner(new UserController(getSession()).read(scanner));
        }

        return category;
    }

    @Override
    public void delete(Scanner scanner) {
        FinancialCategory category = read(scanner);
        if(category == null || category.isDeleted())
            return;

        if(parent != null && !UserInput.getUserConfirmation(scanner, String.format("This is a subcategory of %s category.", parent.toShortString()))) {
            return;
        }

        if(parent != null)
            parent.getSubcategories().removeIf(c -> c.getOid() == category.getOid());

        category.setDeleted(true);
    }


    @Override
    protected String getManagedObjectName() {
        return "Financial Category";
    }

    private final FinancialCategory parent;
}
