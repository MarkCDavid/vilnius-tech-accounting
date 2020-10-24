package vilnius.tech.session;

import vilnius.tech.dal.Address;
import vilnius.tech.dal.BaseOid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Session implements Serializable {

    public Session() {
        this.data = new HashMap<>();
        this.sequence = new SessionSequence();
    }

    public <T extends BaseOid> void add(Class<T> classInfo, BaseOid value) {
        ensureCollectionExists(classInfo);

        value.setOid(sequence.next(classInfo));
        data.get(classInfo).put(value);
    }

    public <T extends BaseOid> T get(Class<T> classInfo, int oid) {
        ensureCollectionExists(classInfo);

        return classInfo.cast(data.get(classInfo).get(oid));
    }

    public <T extends BaseOid> List<T> query(Class<T> classInfo, Predicate<BaseOid> predicate) {
        ensureCollectionExists(classInfo);

        return data.get(classInfo).query(predicate).stream().map(classInfo::cast).collect(Collectors.toList());
    }

    private <T extends BaseOid> void ensureCollectionExists(Class<T> type) {
        if (!data.containsKey(type))
            data.put(type, new BaseOidCollection(type));
    }


    private final Map<Class<? extends BaseOid>, BaseOidCollection> data;
    private final SessionSequence sequence;
}
