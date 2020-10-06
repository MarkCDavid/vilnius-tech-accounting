package vilnius.tech.dal;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Expense extends BaseOid implements Serializable {

    public Expense(Session session) {
        super(session);
    }

    public ExpenseType getType() {
        return type;
    }

    public void setType(ExpenseType type) {
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

    private ExpenseType type;
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
        return String.format("%s, %s. %s expense at %s",
                type != null ? type.toShortString() : null,
                owner != null ? owner.toShortString() : null,
                sum,
                DATE_FORMAT.format(dateTime));
    }
}
