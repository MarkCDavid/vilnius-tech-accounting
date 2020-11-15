package vilnius.tech.utils;

import javafx.scene.control.DatePicker;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {

    public static Timestamp now() {
        return Timestamp.from(nowZoned().toInstant());
    }

    public static ZonedDateTime nowZoned() {
        return ZonedDateTime.now(ZoneId.systemDefault());
    }

    public static Timestamp of(DatePicker picker, Instant _default) {
        if(picker.getValue() == null)
            return Timestamp.from(_default);

        return Timestamp.from(Instant.ofEpochSecond(picker.getValue().toEpochDay() * SECONDS_PER_DAY));
    }

    public static LocalDate monthStart() {
        var now = nowZoned();
        return LocalDate.of(now.getYear(), now.getMonth(), 1);
    }

    public static LocalDate monthEnd() {
       return monthStart().plusMonths(1);
    }



    static final int HOURS_PER_DAY = 24;
    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
    static final int SECONDS_PER_DAY = SECONDS_PER_HOUR * HOURS_PER_DAY;

    private TimeUtils() { }
}
