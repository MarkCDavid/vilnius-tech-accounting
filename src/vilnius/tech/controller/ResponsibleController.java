package vilnius.tech.controller;

import vilnius.tech.dal.*;
import vilnius.tech.utils.Selector;
import vilnius.tech.utils.UserInput;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class ResponsibleController extends CRUDManager<User> implements CRUD<User> {



    public ResponsibleController(Session session, List<User> responsible) {
        super(session);
        this.responsible = responsible;
    }

    @Override
    public User create(Scanner scanner) {
        User user = new UserController(getSession()).read(scanner);
        this.responsible.add(user);
        return user;
    }

    @Override
    public User read(Scanner scanner) {
        return Selector.readViaOid(this, scanner);
    }

    @Override
    public User read(Scanner scanner, boolean create) {
        return read(scanner);
    }

    @Override
    public List<User> readAll() {
        return responsible;
    }

    @Override
    public User update(Scanner scanner) {
        User change = read(scanner);
        User to = new UserController(getSession()).read(scanner);

        int index = responsible.indexOf(change);
        responsible.remove(index);
        responsible.add(index, to);

        return to;
    }

    @Override
    public void delete(Scanner scanner) {
        responsible.remove(read(scanner));
    }


    @Override
    protected String getManagedObjectName() {
        return "Responsible Users";
    }
    private final List<User> responsible;
}
