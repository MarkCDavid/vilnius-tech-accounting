package vilnius.tech.controller;

import vilnius.tech.dal.Address;
import vilnius.tech.dal.BaseOid;
import vilnius.tech.dal.Session;
import vilnius.tech.utils.Display;
import vilnius.tech.utils.Selector;

import java.util.*;
import java.util.function.Predicate;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class CRUDManager<T extends BaseOid> extends Manager implements CRUD<T> {

    public CRUDManager(Session session) {
        super(session);
        references = new HashMap<>();
        initializeReferenceMap(references);
    }

    protected void initializeReferenceMap(Map<String, Reference> referenceMap) { }
    protected abstract String getManagedObjectName();

    @Override
    public T read(Scanner scanner, boolean create) {
        return create ?
                Selector.readViaOidOrCreate(this, scanner) :
                Selector.readViaOid(this, scanner);
    }

    @Override
    public T read(Scanner scanner) {
        return read(scanner, false);
    }

    @Override
    protected boolean isRoot() {
        return false;
    }

    private static final String CREATE = "create";
    private static final String READ = "read";
    private static final String READ_ALL = "read all";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String SAVE = "save";

    protected boolean createEnabled() { return true; }
    protected boolean readEnabled() { return true; }
    protected boolean updateEnabled() { return true; }
    protected boolean deleteEnabled() { return true; }

    @Override
    protected void showOptions(Scanner scanner) {
        if(createEnabled())
            System.out.printf("%s - create '%s'%n", CREATE, getManagedObjectName());

        if(readEnabled()) {
            System.out.printf("%s - display '%s'%n", READ, getManagedObjectName());

            System.out.printf("%s - display all '%s'%n", READ_ALL, getManagedObjectName());
        }

        if(updateEnabled())
            System.out.printf("%s - update '%s'%n", UPDATE, getManagedObjectName());
        if(deleteEnabled())
            System.out.printf("%s - delete '%s'%n", DELETE, getManagedObjectName());

        System.out.printf("%s - save%n", SAVE);

        for(String key: references.keySet()) {
            System.out.printf("%s - show references%n", key);
        }
    }

    @Override
    protected void matchOptions(Scanner scanner, String userInput) {
        if(createEnabled() && Objects.equals(userInput, CREATE)) {
            create(scanner);
        }
        else if(readEnabled() && Objects.equals(userInput, READ)) {
            handleRead(scanner);
        }
        else if(readEnabled() && Objects.equals(userInput, READ_ALL)) {
            handleReadAll();
        }
        else if(updateEnabled() && Objects.equals(userInput, UPDATE)) {
            update(scanner);
        }
        else if(deleteEnabled() && Objects.equals(userInput, DELETE)) {
            delete(scanner);
        }
        else if(Objects.equals(userInput, SAVE)) {
            Serializer.saveSession(scanner, getSession());
        }
        else if(references.containsKey(userInput)){
            handleReferences(scanner, userInput);
        }
    }

    private void handleRead(Scanner scanner) {
        T value = read(scanner);
        if(value == null) {
            System.out.println("No value selected!");
        }
        else {
            System.out.println(value);
        }
    }

    private void handleReadAll() {
        if(readAll().isEmpty()) {
            System.out.printf("No %s available.%n", getManagedObjectName());
        }
        else {
            Display.showWithOid(this);
        }
    }

    private void handleReferences(Scanner scanner, String userInput) {
        T value = Selector.readViaOid(this, scanner, false);
        if(value == null)
            return;

        Reference reference = references.get(userInput);
        List<BaseOid> result = getSession().query(reference.getType(), queryReference(value, reference));
        for(BaseOid o: result) {
            System.out.printf("%s) %s%n", o.getOid(), o.toShortString());
        }
    }

    private Predicate<T> queryReference(T value, Reference reference) {
        return base -> value.getOid() == reference.getOid(base);
    }

    private final Map<String, Reference> references;
}
