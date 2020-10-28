package vilnius.tech.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import vilnius.tech.controller.crud.ExpenseTypeCU;
import vilnius.tech.controller.crud.IncomeTypeCU;
import vilnius.tech.controller.modal.NameCodeModalController;
import vilnius.tech.controller.modal.result.NameCodeModalResult;
import vilnius.tech.dal.Expense;
import vilnius.tech.dal.ExpenseType;
import vilnius.tech.dal.IncomeType;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.utils.MessageBox;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;

import java.io.IOException;

public class ExpenseTypeCRUDListController extends CRUDListController<ExpenseType> {

    public ExpenseTypeCRUDListController(View previousView, User user, Session session) {
        super(previousView, user, session);
    }

    @Override
    protected ObservableList<ExpenseType> getDataSource() {
        return FXCollections.observableArrayList(getSession().query(ExpenseType.class));
    }

    @Override
    protected void onAddNew() throws IOException {
        var modal = new Modal<>(new NameCodeModalController(), getView(), "Add new Expense Type", "namecode.fxml");
        var result = modal.render();
        if(result == null)
            return;

        ExpenseTypeCU.create(getSession(), result.getName(), result.getCode());
    }

    @Override
    protected void onUpdate(ExpenseType item) throws IOException {
        if(item == null)
            return;

        var modal = new Modal<>(new NameCodeModalController(getInitialResult(item)), getView(), "Update Income Type", "namecode.fxml");
        var result = modal.render();
        if(result == null)
            return;

        ExpenseTypeCU.update(item, result.getName(), result.getCode());
    }

    private NameCodeModalResult getInitialResult(ExpenseType item) {
        if(item == null)
            return null;

        var initialResult = new NameCodeModalResult();
        initialResult.setName(item.getName());
        initialResult.setCode(item.getCode());
        return initialResult;
    }
}
