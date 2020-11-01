package vilnius.tech.hibernate;

import java.util.ArrayList;
import java.util.List;

public class FinancialCategory {

    private Integer id;

    private FinancialCategory parent;
    private List<FinancialCategory> children = new ArrayList<>();
    private String name;
    private PhysicalUser owner;
    private List<User> responsibleUsers = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();
    private List<Income> incomes = new ArrayList<>();

    public FinancialCategory() {
    }

    public FinancialCategory(Integer id, FinancialCategory parent, List<FinancialCategory> children, String name, PhysicalUser owner, List<User> responsibleUsers, List<Expense> expenses, List<Income> incomes) {
        this.id = id;
        this.parent = parent;
        this.children = children;
        this.name = name;
        this.owner = owner;
        this.responsibleUsers = responsibleUsers;
        this.expenses = expenses;
        this.incomes = incomes;
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

    public List<FinancialCategory> getChildren() {
        return children;
    }

    public void setChildren(List<FinancialCategory> children) {
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
}
