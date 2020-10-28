package vilnius.tech.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vilnius.tech.controller.crud.IncomeTypeCU;
import vilnius.tech.controller.modal.NameCodeModalController;
import vilnius.tech.controller.modal.result.NameCodeModalResult;
import vilnius.tech.dal.IncomeType;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;

import java.io.IOException;

public class IncomeTypeCRUDListController extends CRUDListController<IncomeType> {

    public IncomeTypeCRUDListController(View previousView, User user, Session session) {
        super(previousView, user, session);
    }

    @Override
    protected ObservableList<IncomeType> getDataSource() {
        return FXCollections.observableArrayList(getSession().query(IncomeType.class));
    }

    @Override
    protected void onAddNew() throws IOException {
        var modal = new Modal<>(new NameCodeModalController(), getView(), "Add new Income Type", "namecode.fxml");
        var result = modal.render();
        if(result == null)
            return;

        IncomeTypeCU.create(getSession(), result.getName(), result.getCode());
    }

    @Override
    protected void onUpdate(IncomeType item) throws IOException {
        if(item == null)
            return;

        var modal = new Modal<>(new NameCodeModalController(getInitialResult(item)), getView(), "Update Income Type", "namecode.fxml");
        var result = modal.render();
        if(result == null)
            return;

        IncomeTypeCU.update(item, result.getName(), result.getCode());
    }

    private NameCodeModalResult getInitialResult(IncomeType item) {
        if(item == null)
            return null;

        var initialResult = new NameCodeModalResult();
        initialResult.setName(item.getName());
        initialResult.setCode(item.getCode());
        return initialResult;
    }
}
