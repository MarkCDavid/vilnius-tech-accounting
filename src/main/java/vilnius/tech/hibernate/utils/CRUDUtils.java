package vilnius.tech.hibernate.utils;

import vilnius.tech.hibernate.BaseEntity;

import java.util.Collection;
import java.util.Objects;

public class CRUDUtils {

    public static <T extends BaseEntity> void remove(Collection<T> from, T toRemove) {
        from.removeIf(item -> {
            if(item == null || toRemove == null)
                return false;
            return Objects.equals(item.getId(), toRemove.getId());
        });
    }

    private CRUDUtils() { }
}
