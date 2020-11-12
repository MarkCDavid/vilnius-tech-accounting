package vilnius.tech.view.controller.modal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import vilnius.tech.hibernate.IncomeType;
import vilnius.tech.hibernate.controller.IncomeTypeService;
import vilnius.tech.view.controller.modal.result.CashflowModalResult;


public class IncomeModalController extends CashflowModalController<IncomeType> {


    public IncomeModalController(Session session) {
        this(session, null);
    }

    public IncomeModalController(Session session, CashflowModalResult<IncomeType> result) {
        super(session, result);
        this.controller = new IncomeTypeService(session);
    }

    @Override
    protected ObservableList<IncomeType> getTypes() {
        return FXCollections.observableArrayList(controller.find());
    }

    private final IncomeTypeService controller;
}
