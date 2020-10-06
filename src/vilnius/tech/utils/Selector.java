package vilnius.tech.utils;

import vilnius.tech.controller.CRUD;
import vilnius.tech.dal.BaseOid;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class Selector {

    public static <T extends BaseOid> T readViaOidOrCreate(CRUD<T> crud, Scanner scanner) {
        if(crud.readAll().isEmpty())
            return crud.create(scanner);

        return readViaOid(crud, scanner);
    }

    public static <T extends BaseOid> T readViaOid(CRUD<T> crud, Scanner scanner) {
       return readViaOid(crud, scanner, true);
    }

    public static <T extends BaseOid> T readViaOid(CRUD<T> crud, Scanner scanner, boolean allowCreate) {
        Display.showWithOid(crud);

        if(allowCreate)
            System.out.println("create - create new");

        System.out.println("quit - quit without choosing");

        String userChoice = UserInput.getString(scanner, "choose");
        if(Objects.equals(userChoice, "quit")) {
            return null;
        }

        if (allowCreate && Objects.equals(userChoice, "create")) {
            return crud.create(scanner);
        }

        Optional<Integer> optionalOid = UserInput.toInteger(userChoice);

        if(optionalOid.isEmpty())
            return null;

        return Filters.filterByOid(crud.readAll(), optionalOid.get());
    }

    private Selector() {}
}
