package vilnius.tech.view.controller.modal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import vilnius.tech.hibernate.ExpenseType;
import vilnius.tech.hibernate.controller.ExpenseTypeService;
import vilnius.tech.view.controller.modal.result.CashflowModalResult;


public class ExpenseModalController extends CashflowModalController<ExpenseType> {


    public ExpenseModalController(Session session) {
        this(session, null);
    }

    public ExpenseModalController(Session session, CashflowModalResult<ExpenseType> result) {
        super(session, result);
        this.controller = new ExpenseTypeService(session);
    }

    @Override
    protected ObservableList<ExpenseType> getTypes() {
        return FXCollections.observableArrayList(controller.find());
    }

    private final ExpenseTypeService controller;
}
