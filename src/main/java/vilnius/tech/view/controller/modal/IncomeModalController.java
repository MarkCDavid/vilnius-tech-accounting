package vilnius.tech.view.controller.modal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vilnius.tech.view.controller.modal.result.CashflowModalResult;
import vilnius.tech.dal.IncomeType;
import vilnius.tech.session.Session;


public class IncomeModalController extends CashflowModalController<IncomeType> {


    public IncomeModalController(Session session) {
        super(session);
    }

    public IncomeModalController(Session session, CashflowModalResult<IncomeType> result) {
        super(session, result);
    }

    @Override
    protected ObservableList<IncomeType> getTypes() {
        return FXCollections.observableArrayList(getSession().query(IncomeType.class));
    }
}
