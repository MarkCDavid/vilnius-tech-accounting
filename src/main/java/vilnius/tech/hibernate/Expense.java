package vilnius.tech.hibernate;

import java.sql.Timestamp;

public class Expense extends Flow {

    private ExpenseType type;

    public Expense(User owner, long sum, Timestamp timestamp, FinancialCategory category, ExpenseType type) {
        super(owner, sum, timestamp, category);
        this.type = type;
    }

    public Expense() {
    }

    public ExpenseType getType() {
        return type;
    }

    public void setType(ExpenseType type) {
        this.type = type;
    }

}
