package vilnius.tech.utils;

import vilnius.tech.dal.BaseOid;

import java.util.List;

public class Filters {


    public static <T extends BaseOid> T filterByOid(List<T> items, int oid) {
        return items.stream()
                .filter(baseOid -> baseOid.getOid() == oid)
                .findFirst().orElse(null);
    }

    private Filters() {

    }
}
