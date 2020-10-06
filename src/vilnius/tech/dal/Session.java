package vilnius.tech.dal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Session implements Serializable {

    public Session() {
        this.sessionData = new HashMap<>();
    }

    public void add(Class<? extends BaseOid> type, BaseOid value) {
        ensureHandlerContainerExists(type);
        value.setOid(getCore(type).size());
        getCore(type).add(value);
    }

    public <T extends BaseOid> List<T> query(Class<T> type, Predicate<T> predicate) {
        return query(type, predicate, false);
    }

    public <T extends BaseOid> List<T> query(Class<T> type, Predicate<T> predicate, boolean queryDeleted) {
        return get(type, queryDeleted).stream().filter(predicate).collect(Collectors.toList());
    }

    public <T extends BaseOid> List<T> get(Class<T> type) {
        return get(type, false);
    }

    public <T extends BaseOid> List<T> get(Class<T> type, boolean getDeleted) {
        if(getDeleted)
            return (List<T>) getCore(type);
        return (List<T>) getCore(type).stream().filter(oid -> !oid.isDeleted()).collect(Collectors.toList());
    }

    private List<BaseOid> getCore(Class<? extends BaseOid> type) {
        ensureHandlerContainerExists(type);
        return sessionData.get(type);
    }

    private <T extends BaseOid> void ensureHandlerContainerExists(Class<T> type) {
        if (!sessionData.containsKey(type))
            sessionData.put(type, new ArrayList<>());
    }


    private final Map<Class<? extends BaseOid>, List<BaseOid>> sessionData;
}
