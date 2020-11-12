package vilnius.tech.utils;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {

    public static Timestamp now() {
        return Timestamp.from(ZonedDateTime.now(ZoneId.systemDefault()).toInstant());
    }

    private TimeUtils() { }
}
