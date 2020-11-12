package vilnius.tech.hibernate;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class Flow implements BaseEntity  {

    private Integer id;
    private User owner;
    private long sum;
    private Timestamp timestamp;
    private FinancialCategory category;

    public Flow() {
    }

    public Flow(User owner, long sum, Timestamp timestamp, FinancialCategory category) {
        this.owner = owner;
        this.sum = sum;
        this.timestamp = timestamp;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp dateTime) {
        this.timestamp = dateTime;
    }

    public FinancialCategory getCategory() {
        return category;
    }

    public void setCategory(FinancialCategory category) {
        this.category = category;
    }
}
