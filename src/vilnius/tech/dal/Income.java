package vilnius.tech.dal;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Income extends BaseOid implements Serializable {

    public Income(Session session) {
        super(session);
    }

    public IncomeType getType() {
        return type;
    }

    public void setType(IncomeType type) {
        this.type = type;
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

    private IncomeType type;
    private User owner;
    private long sum;
    private ZonedDateTime dateTime;

    @Override
    public String toShortString() {
        return null;
    }
}
