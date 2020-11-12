package vilnius.tech.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import vilnius.tech.hibernate.ExpenseType;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.controller.ExpenseTypeService;
import vilnius.tech.utils.GUIUtils;
import vilnius.tech.view.controller.modal.NameCodeModalController;
import vilnius.tech.view.controller.modal.result.NameCodeModalResult;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;

import java.io.IOException;

public class ExpenseTypeCRUDListController extends CRUDListController<ExpenseType> {

    public ExpenseTypeCRUDListController(View previousView, User user, Session session) {
        super(previousView, user, session);
        this.controller = new ExpenseTypeService(session);
    }

    @Override
    protected ObservableList<ExpenseType> getDataSource() {
        return FXCollections.observableArrayList(controller.find());
    }

    @Override
    protected void onAddNew() throws IOException {
        var modal = new Modal<>(new NameCodeModalController(), getView(), "Add new Expense Type", "namecode.fxml");
        var result = modal.render();
        if(result == null)
            return;

        controller.create(result.getName(), result.getCode());
    }

    @Override
    protected void onUpdate(ExpenseType item) throws IOException {
        if(item == null)
            return;

        var modal = new Modal<>(new NameCodeModalController(getInitialResult(item)), getView(), "Update Expense Type", "namecode.fxml");
        var result = modal.render();
        if(result == null)
            return;

        controller.update(item, result.getName(), result.getCode());
    }

    @Override
    protected void onDelete(ExpenseType item) {
        controller.remove(item);
    }


    @Override
    protected void initializeColumns() {
        var table = getTableView();
        table.getColumns().add(GUIUtils.createColumn("Name", "name"));
        table.getColumns().add(GUIUtils.createColumn("Code", "code"));
    }


    private NameCodeModalResult getInitialResult(ExpenseType item) {
        if(item == null)
            return null;

        var initialResult = new NameCodeModalResult();
        initialResult.setName(item.getName());
        initialResult.setCode(item.getCode());
        return initialResult;
    }

    private final ExpenseTypeService controller;
}
