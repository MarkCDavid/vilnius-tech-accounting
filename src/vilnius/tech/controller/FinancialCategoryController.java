package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.UserInput;

import java.util.*;

public class FinancialCategoryController extends CRUDManager<FinancialCategory> implements CRUD<FinancialCategory> {

    public FinancialCategoryController(Session session, FinancialCategory parent) {
        super(session);
        this.currentCategory = parent;
    }

    @Override
    protected boolean createEnabled() {
        return false;
    }

    @Override
    protected boolean updateEnabled() {
        return canManage();
    }

    @Override
    protected boolean deleteEnabled() {
        return canManage();
    }

    private static final String MANAGE_RESPONSIBLE = "responsible";
    private static final String MANAGE_SUBCATEGORY = "subcategories";
    private static final String MANAGE_CATEGORY = "categories";
    private static final String MANAGE_INCOME = "income";
    private static final String MANAGE_EXPENSES = "expenses";

    private static String getCategoryManagementCommand(FinancialCategory parent) {
        return parent == null ? MANAGE_CATEGORY : MANAGE_SUBCATEGORY;
    }

    private boolean canManage() {
        return currentCategory != null;
    }


    @Override
    protected void showOptions(Scanner scanner) {
        if(canManage())
            System.out.printf("Currently managing category: %n%s%n%n", currentCategory);
        super.showOptions(scanner);

        if(canManage())
            System.out.printf("%s - manage users responsible for this category%n", MANAGE_RESPONSIBLE);

        System.out.printf("%s - manage categories or subcategories%n", getCategoryManagementCommand(currentCategory));

        if(canManage())
            System.out.printf("%s - manage income of this category%n", MANAGE_INCOME);

        if(canManage())
            System.out.printf("%s - manage expenses of this category%n", MANAGE_EXPENSES);
    }

    @Override
    protected void matchOptions(Scanner scanner, String userInput) {
        super.matchOptions(scanner, userInput);
        if(canManage() && Objects.equals(userInput, MANAGE_RESPONSIBLE)) {
            new ResponsibleController(getSession(), currentCategory.getResponsibleUsers()).manage(scanner);
        }
        else if(Objects.equals(userInput, getCategoryManagementCommand(currentCategory))) {
            FinancialCategory category = read(scanner);
            new FinancialCategoryController(getSession(), category).manage(scanner);
        }
        else if(canManage() && Objects.equals(userInput, MANAGE_INCOME)) {
            new IncomeController(getSession(), currentCategory).manage(scanner);
        }
        else if(canManage() && Objects.equals(userInput, MANAGE_EXPENSES)) {
            new ExpenseController(getSession(), currentCategory).manage(scanner);
        }
    }

    @Override
    public FinancialCategory create(Scanner scanner) {
        String name = UserInput.getString(scanner, "Category Name");

        System.out.println("Owner: ");
        User owner = new UserController(getSession()).read(scanner);

        FinancialCategory category = new FinancialCategory(getSession());
        category.setName(name);
        category.setOwner(owner);
        return category;
    }

    @Override
    public List<FinancialCategory> readAll() {
        return getSession().query(FinancialCategory.class, fc -> fc.getParent() == currentCategory);
    }

    @Override
    public FinancialCategory update(Scanner scanner) {
        if(UserInput.getUserConfirmation(scanner, "Update Category Name")) {
            currentCategory.setName(UserInput.getString(scanner, "Category Name"));
        }

        if(UserInput.getUserConfirmation(scanner, "Update Owner")) {
            currentCategory.setOwner(new UserController(getSession()).read(scanner));
        }

        return currentCategory;
    }

    @Override
    public void delete(Scanner scanner) {
        if(currentCategory == null || currentCategory.isDeleted())
            return;

        if(currentCategory.getParent() != null && !UserInput.getUserConfirmation(scanner, String.format("This is a subcategory of %s category.", currentCategory.getParent().toShortString()))) {
            return;
        }

        if(currentCategory.getParent() != null)
            currentCategory.getSubcategories().removeIf(c -> c.getOid() == currentCategory.getOid());

        currentCategory.setDeleted(true);
        forceQuit();
    }


    @Override
    protected String getManagedObjectName() {
        return "Financial Category";
    }

    private final FinancialCategory currentCategory;
}
