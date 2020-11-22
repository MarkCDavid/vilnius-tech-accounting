package vilnius.tech.hibernate;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class Income extends Flow {

    @Expose
    private IncomeType type;

    public Income(User owner, long sum, Timestamp timestamp, FinancialCategory category, IncomeType type) {
        super(owner, sum, timestamp, category);
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
