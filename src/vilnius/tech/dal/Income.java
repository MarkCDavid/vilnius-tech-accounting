package vilnius.tech.dal;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

    public FinancialCategory getParent() {
        return parent;
    }

    public void setParent(FinancialCategory parent) {
        this.parent = parent;
    }

    private FinancialCategory parent;
    private IncomeType type;
    private User owner;
    private long sum;
    private ZonedDateTime dateTime;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("HH:mm").withZone(ZoneId.systemDefault());

    @Override
    public String toString() {
        return formatReference(type, "Type") +
                formatReference(owner, "Owner") +
                formatValue(sum, "Sum") +
                formatValue(DATE_FORMAT.format(dateTime), "DateTime");
    }

    @Override
    public String toShortString() {
        return String.format("%s, %s. %s income at %s",
                type != null ? type.toShortString() : null,
                owner != null ? owner.toShortString() : null,
                sum,
                DATE_FORMAT.format(dateTime));
    }
}
