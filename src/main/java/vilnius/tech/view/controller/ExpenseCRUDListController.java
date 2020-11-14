package vilnius.tech.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import vilnius.tech.hibernate.Expense;
import vilnius.tech.hibernate.ExpenseType;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.service.ExpenseService;
import vilnius.tech.utils.GUIUtils;
import vilnius.tech.utils.TimeUtils;
import vilnius.tech.view.controller.modal.ExpenseModalController;
import vilnius.tech.view.controller.modal.result.CashflowModalResult;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;

import java.io.IOException;

public class ExpenseCRUDListController extends CRUDListController<Expense> {

    public ExpenseCRUDListController(View previousView, User user, FinancialCategory category, Session session) {
        super(previousView, user, session);
        this.category = category;
        this.expenseService = new ExpenseService(session);
    }

    @Override
    protected ObservableList<Expense> getDataSource() {
        expenseService.fetch("type", "owner", "category");
        return FXCollections.observableArrayList(expenseService.find_Category(category));
    }

    @Override
    protected void onAddNew() throws IOException {
        var modal = new Modal<>(new ExpenseModalController(getSession()), getView(), "Add new Expense", "cashflowTypeModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        expenseService.create(getUser(), result.getSum(), TimeUtils.now(), category, result.getType());
    }

    @Override
    protected void onUpdate(Expense item) throws IOException {
        if(item == null)
            return;

        var modal = new Modal<>(new ExpenseModalController(getSession(), getInitialResult(item)), getView(), "Update Expense", "cashflowTypeModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        expenseService.update(item, getUser(), result.getSum(), TimeUtils.now(), category, result.getType());
    }

    @Override
    protected void onDelete(Expense item) {
        expenseService.remove(item);
    }

    @Override
    protected void initializeColumns() {
        var table = getTableView();
        table.getColumns().add(GUIUtils.createColumn("Owner", "owner"));
        table.getColumns().add(GUIUtils.createColumn("Sum", "sum"));
        table.getColumns().add(GUIUtils.createColumn("Date", "timestamp"));
        table.getColumns().add(GUIUtils.createColumn("Type", "type"));
    }

    private CashflowModalResult<ExpenseType> getInitialResult(Expense item) {
        if(item == null)
            return null;

        var initialResult = new CashflowModalResult<ExpenseType>();
        initialResult.setType(item.getType());
        initialResult.setSum(item.getSum());
        return initialResult;
    }

    private final FinancialCategory category;
    private final ExpenseService expenseService;
}
