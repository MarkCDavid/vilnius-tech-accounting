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
    private Long sum;

    @Expose
    private Timestamp timestamp;

    private FinancialCategory category;

    public Flow() {
    }

    public Flow(User owner, Long sum, Timestamp timestamp, FinancialCategory category) {
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

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
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
