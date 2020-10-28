package vilnius.tech.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vilnius.tech.controller.crud.IncomeCU;
import vilnius.tech.controller.modal.IncomeModalController;
import vilnius.tech.controller.modal.result.CashflowModalResult;
import vilnius.tech.dal.Income;
import vilnius.tech.dal.IncomeType;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;

import java.io.IOException;

public class IncomeCRUDListController extends CRUDListController<Income> {

    public IncomeCRUDListController(View previousView, User user, Session session) {
        super(previousView, user, session);
    }

    @Override
    protected ObservableList<Income> getDataSource() {
        return FXCollections.observableArrayList(getSession().query(Income.class));
    }

    @Override
    protected void onAddNew() throws IOException {
        var modal = new Modal<>(new IncomeModalController(getSession()), getView(), "Add new Income", "cashflowTypeModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        IncomeCU.create(getSession(), result.getType(), getUser(), result.getSum());
    }

    @Override
    protected void onUpdate(Income item) throws IOException {
        if(item == null)
            return;

        var modal = new Modal<>(new IncomeModalController(getSession(), getInitialResult(item)), getView(), "Update Income", "cashflowTypeModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        IncomeCU.update(item, result.getType(), getUser(), result.getSum());
    }

    private CashflowModalResult<IncomeType> getInitialResult(Income item) {
        if(item == null)
            return null;

        var initialResult = new CashflowModalResult<IncomeType>();
        initialResult.setType(item.getType());
        initialResult.setSum(item.getSum());
        return initialResult;
    }
}
