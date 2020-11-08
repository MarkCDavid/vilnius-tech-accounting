//package vilnius.tech.view.controller;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import vilnius.tech.view.controller.crud.ExpenseCU;
//import vilnius.tech.view.controller.crud.ExpenseTypeCU;
//import vilnius.tech.view.controller.modal.CashflowModalController;
//import vilnius.tech.view.controller.modal.ExpenseModalController;
//import vilnius.tech.view.controller.modal.NameCodeModalController;
//import vilnius.tech.view.controller.modal.result.CashflowModalResult;
//import vilnius.tech.view.controller.modal.result.NameCodeModalResult;
//import vilnius.tech.dal.Expense;
//import vilnius.tech.dal.ExpenseType;
//import vilnius.tech.dal.FinancialCategory;
//import vilnius.tech.dal.User;
//import vilnius.tech.session.Session;
//import vilnius.tech.view.Modal;
//import vilnius.tech.view.View;
//
//import java.io.IOException;
//
//public class ExpenseCRUDListController extends CRUDListController<Expense> {
//
//    public ExpenseCRUDListController(View previousView, User user, FinancialCategory category, Session session) {
//        super(previousView, user, session);
//        this.category = category;
//    }
//
//    @Override
//    protected ObservableList<Expense> getDataSource() {
//        return FXCollections.observableArrayList(category.getExpenses());
//    }
//
//    @Override
//    protected void onAddNew() throws IOException {
//        var modal = new Modal<>(new ExpenseModalController(getSession()), getView(), "Add new Expense", "cashflowTypeModal.fxml");
//        var result = modal.render();
//        if(result == null)
//            return;
//
//        category.getExpenses().add(ExpenseCU.create(getSession(), result.getType(), getUser(), result.getSum()));
//
//    }
//
//    @Override
//    protected void onUpdate(Expense item) throws IOException {
//        if(item == null)
//            return;
//
//        var modal = new Modal<>(new ExpenseModalController(getSession(), getInitialResult(item)), getView(), "Update Expense", "cashflowTypeModal.fxml");
//        var result = modal.render();
//        if(result == null)
//            return;
//
//        ExpenseCU.update(item, result.getType(), getUser(), result.getSum());
//    }
//
//    @Override
//    protected void onDelete(Expense item) throws IOException {
//        category.getExpenses().remove(item);
//        super.onDelete(item);
//    }
//
//    private CashflowModalResult<ExpenseType> getInitialResult(Expense item) {
//        if(item == null)
//            return null;
//
//        var initialResult = new CashflowModalResult<ExpenseType>();
//        initialResult.setType(item.getType());
//        initialResult.setSum(item.getSum());
//        return initialResult;
//    }
//
//    private final FinancialCategory category;
//}
