package vilnius.tech.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.Income;
import vilnius.tech.hibernate.IncomeType;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.controller.IncomeService;
import vilnius.tech.utils.GUIUtils;
import vilnius.tech.utils.TimeUtils;
import vilnius.tech.view.controller.modal.IncomeModalController;
import vilnius.tech.view.controller.modal.result.CashflowModalResult;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;

import java.io.IOException;

public class IncomeCRUDListController extends CRUDListController<Income> {

    public IncomeCRUDListController(View previousView, User user, FinancialCategory category, Session session) {
        super(previousView, user, session);
        this.category = category;
        this.controller = new IncomeService(session);
    }

    @Override
    protected ObservableList<Income> getDataSource() {
        return FXCollections.observableArrayList(this.category.getIncomes());
    }

    @Override
    protected void onAddNew() throws IOException {
        var modal = new Modal<>(new IncomeModalController(getSession()), getView(), "Add new Income", "cashflowTypeModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        controller.create(getUser(), result.getSum(), TimeUtils.now(), category, result.getType());
    }

    @Override
    protected void onUpdate(Income item) throws IOException {
        if(item == null)
            return;

        var modal = new Modal<>(new IncomeModalController(getSession(), getInitialResult(item)), getView(), "Update Income", "cashflowTypeModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        controller.update(item, getUser(), result.getSum(), TimeUtils.now(), category, result.getType());
    }

    @Override
    protected void onDelete(Income item) {
        controller.remove(item);
    }

    @Override
    protected void initializeColumns() {
        var table = getTableView();
        table.getColumns().add(GUIUtils.createColumn("Owner", "owner"));
        table.getColumns().add(GUIUtils.createColumn("Sum", "sum"));
        table.getColumns().add(GUIUtils.createColumn("Date", "dateTime"));
        table.getColumns().add(GUIUtils.createColumn("Type", "type"));
    }

    private CashflowModalResult<IncomeType> getInitialResult(Income item) {
        if(item == null)
            return null;

        var initialResult = new CashflowModalResult<IncomeType>();
        initialResult.setType(item.getType());
        initialResult.setSum(item.getSum());
        return initialResult;
    }

    private final FinancialCategory category;
    private final IncomeService controller;
}
