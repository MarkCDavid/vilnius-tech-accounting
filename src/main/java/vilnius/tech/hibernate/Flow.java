package vilnius.tech.hibernate;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class Flow implements BaseEntity  {


    @Expose
    private Integer id;

    @Expose
    private User owner;

    @Expose
    private long sum;

    @Expose
    private Timestamp timestamp;

    @Expose
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
