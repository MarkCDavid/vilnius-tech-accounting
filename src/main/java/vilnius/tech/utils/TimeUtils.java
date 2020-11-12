package vilnius.tech.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {

    public static ZonedDateTime now() {
        return ZonedDateTime.now(ZoneId.systemDefault());
    }

    private TimeUtils() { }
}
