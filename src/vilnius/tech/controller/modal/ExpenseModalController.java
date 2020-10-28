package vilnius.tech.controller.modal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vilnius.tech.controller.modal.result.CashflowModalResult;
import vilnius.tech.dal.ExpenseType;
import vilnius.tech.dal.IncomeType;
import vilnius.tech.session.Session;


public class ExpenseModalController extends CashflowModalController<ExpenseType> {


    public ExpenseModalController(Session session) {
        super(session);
    }

    public ExpenseModalController(Session session, CashflowModalResult<ExpenseType> result) {
        super(session, result);
    }

    @Override
    protected ObservableList<ExpenseType> getTypes() {
        return FXCollections.observableArrayList(getSession().query(ExpenseType.class));
    }
}
