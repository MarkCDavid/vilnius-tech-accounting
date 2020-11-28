package vilnius.tech.view.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.hibernate.Session;
import vilnius.tech.hibernate.Expense;
import vilnius.tech.hibernate.ExpenseType;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.service.ExpenseService;
import vilnius.tech.utils.*;
import vilnius.tech.view.controller.modal.ExpenseModalController;
import vilnius.tech.view.controller.modal.result.CashflowModalResult;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;

public class ExpenseCRUDListController extends CRUDListController<Expense> {

    public ExpenseCRUDListController(View previousView, User user, FinancialCategory category, Session session) {
        super(previousView, user, session);
        this.category = category;
        this.expenseService = new ExpenseService(session);
    }

    @Override
    protected ObservableList<Expense> getDataSource() {
        return FXCollections.observableArrayList(expenseService.find_Category(
                category,
                TimeUtils.of(datePickerFrom, Instant.MIN),
                TimeUtils.of(datePickerTo, Instant.MAX)
        ));
    }

    void handleUpdatedValues() {
        labelTotal.setText(
                String.format("Total: %s",
                        new ExpenseCalculator(category, getSession())
                                .getTotal_IncludeChildren(
                                        TimeUtils.of(datePickerFrom, Instant.MIN),
                                        TimeUtils.of(datePickerTo, Instant.MAX)
                                )
                )
        );
        updateTable();
    }


    @Override
    protected void onAddNew() throws IOException {
        var modal = new Modal<>(new ExpenseModalController(getSession()), getView(), "Add new Expense", "cashflowTypeModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        expenseService.create(getUser(), result.getSum(), TimeUtils.now(), category, result.getType());
        handleUpdatedValues();
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
        handleUpdatedValues();
    }

    @Override
    protected void onDelete(Expense item) {
        expenseService.remove(item);
        handleUpdatedValues();
    }

    @Override
    protected void initializeColumns() {
        var table = getTableView();
        table.getColumns().add(GUIUtils.createColumn_Getter("Owner", "getOwner"));
        table.getColumns().add(GUIUtils.createColumn_Getter("Sum", "getSum"));
        table.getColumns().add(GUIUtils.createColumn_Getter("Date", "getTimestamp"));
        table.getColumns().add(GUIUtils.createColumn_Getter("Type", "getType"));
    }

    private CashflowModalResult<ExpenseType> getInitialResult(Expense item) {
        if(item == null)
            return null;

        var initialResult = new CashflowModalResult<ExpenseType>();
        initialResult.setType(item.getType());
        initialResult.setSum(item.getSum());
        return initialResult;
    }

    @Override
    public void initialize() {
        super.initialize();

        datePickerFrom.setValue(TimeUtils.monthStart());
        datePickerTo.setValue(TimeUtils.monthEnd());

        datePickerFrom.valueProperty().addListener(this::datePickerFromChanged);
        datePickerTo.valueProperty().addListener(this::datePickerToChanged);

        handleUpdatedValues();
    }

    private final Suspender suspender_datePickerToChanged = new Suspender();
    private void datePickerToChanged(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

        if(suspender_datePickerToChanged.isSuspended())
            return;

        if (datePickerFrom.getValue() == null || datePickerFrom.getValue().isAfter(newValue)) {
            try (var suspend = suspender_datePickedFromChanged.suspend()) {
                datePickerFrom.setValue(newValue.minusMonths(1));
            }
        }

        handleUpdatedValues();
    }


    private final Suspender suspender_datePickedFromChanged = new Suspender();
    private void datePickerFromChanged(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

        if(suspender_datePickedFromChanged.isSuspended())
            return;

        if (datePickerTo.getValue() == null || datePickerTo.getValue().isBefore(newValue)) {
            try (var suspend = suspender_datePickerToChanged.suspend()) {
                datePickerTo.setValue(newValue.plusMonths(1));
            }
        }

        handleUpdatedValues();
    }


    private final FinancialCategory category;
    private final ExpenseService expenseService;

    @FXML
    private DatePicker datePickerFrom;

    @FXML
    private DatePicker datePickerTo;

    @FXML
    private Label labelTotal;
}
