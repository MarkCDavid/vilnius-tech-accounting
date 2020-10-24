package vilnius.tech.dal;

import vilnius.tech.session.Session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FinancialCategory extends BaseOid implements Serializable {

    public FinancialCategory(Session session) {
        super(session, FinancialCategory.class);

        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.subcategories = new ArrayList<>();
        this.responsibleUsers = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<FinancialCategory> getSubcategories() {
        return subcategories;
    }

    public List<User> getResponsibleUsers() {
        return responsibleUsers;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public FinancialCategory getParent() {
        return parent;
    }

    public void setParent(FinancialCategory parent) {
        this.parent = parent;
    }

    private FinancialCategory parent;
    private String name;
    private User owner;
    private final List<FinancialCategory> subcategories;
    private final List<User> responsibleUsers;
    private final List<Expense> expenses;
    private final List<Income> incomes;
}
