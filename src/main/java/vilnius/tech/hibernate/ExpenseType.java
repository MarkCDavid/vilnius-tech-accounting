package vilnius.tech.hibernate;

import java.util.ArrayList;
import java.util.List;

public class ExpenseType extends FlowType {

    private List<Expense> expenses = new ArrayList<>();

    public ExpenseType() {
    }

    public ExpenseType(Integer id, String name, String code, List<Expense> expenses) {
        super(id, name, code);
        this.expenses = expenses;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }


}
