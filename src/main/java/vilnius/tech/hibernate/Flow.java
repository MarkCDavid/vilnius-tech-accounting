package vilnius.tech.hibernate;

import java.time.ZonedDateTime;

public class Flow implements BaseEntity  {

    private Integer id;
    private User owner;
    private long sum;
    private ZonedDateTime dateTime;
    private FinancialCategory category;

    public Flow() {
    }

    public Flow(User owner, long sum, ZonedDateTime dateTime, FinancialCategory category) {
        this.owner = owner;
        this.sum = sum;
        this.dateTime = dateTime;
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

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public FinancialCategory getCategory() {
        return category;
    }

    public void setCategory(FinancialCategory category) {
        this.category = category;
    }
}
