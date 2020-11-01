package vilnius.tech.hibernate;

import java.util.HashSet;
import java.util.Set;

public class FinancialCategory {

    private Integer id;

    private FinancialCategory parent;
    private Set<FinancialCategory> children = new HashSet<>();
    private String name;
    private PhysicalUser owner;
    private Set<User> responsibleUsers = new HashSet<>();
    private Set<Expense> expenses = new HashSet<>();
    private Set<Income> incomes = new HashSet<>();

    public FinancialCategory() {
    }

    public FinancialCategory(FinancialCategory parent, String name, PhysicalUser owner) {
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

    public PhysicalUser getOwner() {
        return owner;
    }

    public void setOwner(PhysicalUser owner) {
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
}