package vilnius.tech.dal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FinancialCategory extends BaseOid implements Serializable {

    public FinancialCategory(Session session) {
        super(session);

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

    @Override
    public String toString() {
        return (parent == null? "" : formatReference(parent.toShortString(), "Parent")) +
                String.format("%s%n", toShortString()) +
                formatValue(subcategories.size(), "Subcategories") +
                formatValue(responsibleUsers.size(), "Responsible") +
                formatValue(expenses.size(), "Expenses") +
                formatValue(incomes.size(), "Income");
    }

    @Override
    public String toShortString() {
        return String.format("%s - %s", name, owner != null ? owner.toShortString() : null);
    }
}
