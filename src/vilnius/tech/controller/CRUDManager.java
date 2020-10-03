package vilnius.tech.controller;

import vilnius.tech.dal.BaseOid;
import vilnius.tech.utils.Display;

import java.util.Objects;
import java.util.Scanner;

public abstract class CRUDManager<T extends BaseOid> extends Manager implements CRUD<T> {

    protected abstract String getManagedObjectName();

    @Override
    protected boolean isRoot() {
        return false;
    }


    private static final String CREATE = "create";
    private static final String READ = "read";
    private static final String READ_ALL = "read all";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

    @Override
    protected void showOptions(Scanner scanner) {
        System.out.printf("%s - create '%s'%n", CREATE, getManagedObjectName());
        System.out.printf("%s - display '%s'%n", READ, getManagedObjectName());
        System.out.printf("%s - display all '%s'%n", READ_ALL, getManagedObjectName());
        System.out.printf("%s - update '%s'%n", UPDATE, getManagedObjectName());
        System.out.printf("%s - delete '%s'%n", DELETE, getManagedObjectName());
    }

    @Override
    protected void matchOptions(Scanner scanner, String userInput) {
        if(Objects.equals(userInput, CREATE)) {
            create(scanner);
        }
        else if(Objects.equals(userInput, READ)) {
            T value = read(scanner);
            if(value == null) {
                System.out.println("No value selected!");
            }
            else {
                System.out.println(value);
            }

        }
        else if(Objects.equals(userInput, READ_ALL)) {
            if(readAll().isEmpty()) {
                System.out.printf("No %s available.%n", getManagedObjectName());
            }
            else {
                Display.showWithOid(this);
            }
        }
        else if(Objects.equals(userInput, UPDATE)) {
            System.out.println("NOT IMPLEMENTED");
        }
        else if(Objects.equals(userInput, DELETE)) {
            System.out.println("NOT IMPLEMENTED");
        }
    }
}
