package vilnius.tech.dal;

import java.io.Serializable;
import java.util.List;

public class FinancialCategory extends BaseOid implements Serializable {

    public FinancialCategory(Session session) {
        super(session);
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

    public void setSubcategories(List<FinancialCategory> subcategories) {
        this.subcategories = subcategories;
    }

    public List<User> getResponsibleUsers() {
        return responsibleUsers;
    }

    public void setResponsibleUsers(List<User> responsibleUsers) {
        this.responsibleUsers = responsibleUsers;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
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
    private List<FinancialCategory> subcategories;
    private List<User> responsibleUsers;
    private List<Expense> expenses;
    private List<Income> incomes;

    @Override
    public String toString() {
        return parent == null? "" : formatReference(parent.toShortString(), "Parent") +
                formatReference(name, "Name") +
                formatReference(owner, "Owner") +
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
