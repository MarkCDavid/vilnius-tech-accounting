package vilnius.tech.hibernate;

import java.time.ZonedDateTime;

public class Income extends Flow {

    private IncomeType type;

    public Income(User owner, long sum, ZonedDateTime dateTime, FinancialCategory category, IncomeType type) {
        super(owner, sum, dateTime, category);
        this.type = type;
    }

    public Income() {

    }

    public IncomeType getType() {
        return type;
    }

    public void setType(IncomeType type) {
        this.type = type;
    }

}
