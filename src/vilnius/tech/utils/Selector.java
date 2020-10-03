package vilnius.tech.utils;

import vilnius.tech.controller.CRUD;
import vilnius.tech.dal.BaseOid;

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
        Display.showWithOid(crud);
        System.out.println("create - create new");
        System.out.println("quit - quit without choosing");

        String userChoice = UserInput.getString(scanner, "choose");
        if(Objects.equals(userChoice, "quit")) {
            return null;
        }

        if(Objects.equals(userChoice, "create")) {
            return crud.create(scanner);
        }

        Optional<Integer> optionalOid = UserInput.toOid(userChoice);

        if(optionalOid.isEmpty())
            return null;

        return Filters.filterByOid(crud.readAll(), optionalOid.get());
    }

    private Selector() {}
}
