package vilnius.tech.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import vilnius.tech.hibernate.IncomeType;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.service.IncomeTypeService;
import vilnius.tech.utils.GUIUtils;
import vilnius.tech.view.controller.modal.NameCodeModalController;
import vilnius.tech.view.controller.modal.result.NameCodeModalResult;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;

import java.io.IOException;

public class IncomeTypeCRUDListController extends CRUDListController<IncomeType> {

    public IncomeTypeCRUDListController(View previousView, User user, Session session) {
        super(previousView, user, session);
        this.controller = new IncomeTypeService(session);
    }

    @Override
    protected ObservableList<IncomeType> getDataSource() {
        return FXCollections.observableArrayList(controller.find());
    }

    @Override
    protected void onAddNew() throws IOException {
        var modal = new Modal<>(new NameCodeModalController(), getView(), "Add new Income Type", "namecode.fxml");
        var result = modal.render();
        if(result == null)
            return;

        controller.create(result.getName(), result.getCode());
    }

    @Override
    protected void onUpdate(IncomeType item) throws IOException {
        if(item == null)
            return;

        var modal = new Modal<>(new NameCodeModalController(getInitialResult(item)), getView(), "Update Income Type", "namecode.fxml");
        var result = modal.render();
        if(result == null)
            return;

        controller.update(item, result.getName(), result.getCode());
    }

    @Override
    protected void onDelete(IncomeType item) {
        controller.remove(item);
    }

    @Override
    protected void initializeColumns() {
        var table = getTableView();
        table.getColumns().add(GUIUtils.createColumn_Getter("Name", "getName"));
        table.getColumns().add(GUIUtils.createColumn_Getter("Code", "getCode"));
    }

    private NameCodeModalResult getInitialResult(IncomeType item) {
        if(item == null)
            return null;

        var initialResult = new NameCodeModalResult();
        initialResult.setName(item.getName());
        initialResult.setCode(item.getCode());
        return initialResult;
    }

    private final IncomeTypeService controller;
}
