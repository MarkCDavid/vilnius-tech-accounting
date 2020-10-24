package vilnius.tech.session;

import vilnius.tech.dal.BaseOid;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BaseOidCollection implements Serializable {

    public BaseOidCollection(Class<? extends BaseOid> classInfo) {
        this.data = new HashMap<>();
        this.classInfo = classInfo;
    }

    public <T extends BaseOid> void put(T object) {
        this.data.put(object.getOid(), classInfo.cast(object));
    }

    public BaseOid get(int oid) {
        if(!data.containsKey(oid))
            return null;

        return data.get(oid);
    }

    public List<BaseOid> query(Predicate<BaseOid> predicate) {
        return data.values().stream().filter(predicate).collect(Collectors.toList());
    }

    private final Map<Integer, BaseOid> data;
    private final Class<? extends BaseOid> classInfo;
}
