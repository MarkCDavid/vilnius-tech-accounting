package vilnius.tech.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import vilnius.tech.hibernate.Expense;
import vilnius.tech.hibernate.ExpenseType;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.controller.ExpenseService;
import vilnius.tech.hibernate.controller.FinancialCategoryService;
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
        this.financialCategoryController = new FinancialCategoryService(session);
        this.expenseController = new ExpenseService(session);
        this.category = category;
    }

    @Override
    protected ObservableList<Expense> getDataSource() {
        return FXCollections.observableArrayList(category.getExpenses());
    }

    @Override
    protected void onAddNew() throws IOException {
        var modal = new Modal<>(new ExpenseModalController(getSession()), getView(), "Add new Expense", "cashflowTypeModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        expenseController.create(getUser(), result.getSum(), TimeUtils.now(), category, result.getType());
    }

    @Override
    protected void onUpdate(Expense item) throws IOException {
        if(item == null)
            return;

        var modal = new Modal<>(new ExpenseModalController(getSession(), getInitialResult(item)), getView(), "Update Expense", "cashflowTypeModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        expenseController.update(item, getUser(), result.getSum(), TimeUtils.now(), category, result.getType());
    }

    @Override
    protected void onDelete(Expense item) {
        expenseController.remove(item);
    }

    @Override
    protected void initializeColumns() {
        var table = getTableView();
        table.getColumns().add(GUIUtils.createColumn("Owner", "owner"));
        table.getColumns().add(GUIUtils.createColumn("Sum", "sum"));
        table.getColumns().add(GUIUtils.createColumn("Date", "dateTime"));
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
    private final FinancialCategoryService financialCategoryController;
    private final ExpenseService expenseController;
}
