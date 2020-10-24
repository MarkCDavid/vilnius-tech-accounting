package vilnius.tech.utils;

import vilnius.tech.dal.BaseOid;


public class Display {

    public static <T extends BaseOid> void showWithOid(CRUD<T> crud) {
        for(T baseOid : crud.readAll()) {
            System.out.printf("%d) %s%n", baseOid.getOid(), baseOid.toShortString());
        }
    }

    private Display() {

    }
}
