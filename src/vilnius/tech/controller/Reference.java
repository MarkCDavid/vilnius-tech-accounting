package vilnius.tech.controller;

import vilnius.tech.dal.BaseOid;

import java.util.function.Function;

public class Reference<T extends BaseOid> {

    public Reference(Class<T> type, Function<T, Integer> oidRetriever) {
        this.type = type;
        this.oidRetriever = oidRetriever;
    }

    Class<T> type;
    Function<T, Integer> oidRetriever;

    public Class<T> getType() {
        return type;
    }

    public Integer getOid(T value) {
        return oidRetriever.apply(value);
    }


}
