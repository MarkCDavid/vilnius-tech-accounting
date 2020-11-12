package vilnius.tech.hibernate;

import java.util.HashSet;
import java.util.Set;

public class ExpenseType extends FlowType {

    private Set<Expense> expenses = new HashSet<>();

    public ExpenseType() {
    }

    public ExpenseType(String name, String code) {
        super(name, code);
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }


}
