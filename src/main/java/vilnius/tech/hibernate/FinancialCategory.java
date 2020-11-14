package vilnius.tech.hibernate;

import java.util.HashSet;
import java.util.Set;

public class FinancialCategory implements BaseEntity  {

    private Integer id;

    private FinancialCategory parent;
    private Set<FinancialCategory> children;
    private String name;
    private User owner;
    private Set<User> responsibleUsers;
    private Set<Expense> expenses;
    private Set<Income> incomes;

    public FinancialCategory() {
    }

    public FinancialCategory(FinancialCategory parent, String name, User owner) {
        this.parent = parent;
        this.name = name;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FinancialCategory getParent() {
        return parent;
    }

    public void setParent(FinancialCategory parent) {
        this.parent = parent;
    }

    public Set<FinancialCategory> getChildren() {
        return children;
    }

    public void setChildren(Set<FinancialCategory> children) {
        this.children = children;
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

    public Set<User> getResponsibleUsers() {
        return responsibleUsers;
    }

    public void setResponsibleUsers(Set<User> responsibleUsers) {
        this.responsibleUsers = responsibleUsers;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }

    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }

    @Override
    public String toString() {
        return name;
    }
}
