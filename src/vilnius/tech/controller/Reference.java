package vilnius.tech.controller;

import vilnius.tech.dal.BaseOid;

import java.util.function.Function;
import java.util.function.Predicate;

public class Reference<T extends BaseOid> {

    public Reference(Class<T> type, Function<T, Integer> matchFetcher) {
        this.type = type;
        this.matchFetcher = matchFetcher;
    }

    Class<T> type;
    Function<T, Integer> matchFetcher;

    public Class<T> getType() {
        return type;
    }

    public Integer getOid(T value) {
        return matchFetcher.apply(value);
    }


}
