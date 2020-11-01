package vilnius.tech.hibernate;

import java.time.ZonedDateTime;


public class Expense extends Flow {

    private ExpenseType type;

    public Expense(User owner, long sum, ZonedDateTime dateTime, FinancialCategory category, ExpenseType type) {
        super(owner, sum, dateTime, category);
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
