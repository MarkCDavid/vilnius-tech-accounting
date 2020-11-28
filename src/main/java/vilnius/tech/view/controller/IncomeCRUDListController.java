package vilnius.tech.view.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import org.hibernate.Session;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.Income;
import vilnius.tech.hibernate.IncomeType;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.service.IncomeService;
import vilnius.tech.utils.GUIUtils;
import vilnius.tech.utils.IncomeCalculator;
import vilnius.tech.utils.Suspender;
import vilnius.tech.utils.TimeUtils;
import vilnius.tech.view.controller.modal.IncomeModalController;
import vilnius.tech.view.controller.modal.result.CashflowModalResult;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;

public class IncomeCRUDListController extends CRUDListController<Income> {

    public IncomeCRUDListController(View previousView, User user, FinancialCategory category, Session session) {
        super(previousView, user, session);
        this.incomeService = new IncomeService(session);
        this.category = category;
    }

    @Override
    protected ObservableList<Income> getDataSource() {
        return FXCollections.observableArrayList(
                incomeService.find_Category(
                        category,
                        TimeUtils.of(datePickerFrom, Instant.MIN),
                        TimeUtils.of(datePickerTo, Instant.MAX)
                )
        );
    }

    @Override
    protected void onAddNew() throws IOException {
        var modal = new Modal<>(new IncomeModalController(getSession()), getView(), "Add new Income", "cashflowTypeModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        incomeService.create(getUser(), result.getSum(), TimeUtils.now(), category, result.getType());
        handleUpdatedValues();
    }

    @Override
    protected void onUpdate(Income item) throws IOException {
        if(item == null)
            return;

        var modal = new Modal<>(new IncomeModalController(getSession(), getInitialResult(item)), getView(), "Update Income", "cashflowTypeModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        incomeService.update(item, getUser(), result.getSum(), TimeUtils.now(), category, result.getType());
        handleUpdatedValues();
    }

    void handleUpdatedValues() {
        labelTotal.setText(
                String.format("Total: %s",
                        new IncomeCalculator(category, getSession())
                                .getTotal_IncludeChildren(
                                        TimeUtils.of(datePickerFrom, Instant.MIN),
                                        TimeUtils.of(datePickerTo, Instant.MAX)
                                )
                )
        );
        updateTable();
    }

    @Override
    protected void onDelete(Income item) {
        incomeService.remove(item);
    }

    @Override
    protected void initializeColumns() {
        var table = getTableView();
        table.getColumns().add(GUIUtils.createColumn_Getter("Owner", "getOwner"));
        table.getColumns().add(GUIUtils.createColumn_Getter("Sum", "getSum"));
        table.getColumns().add(GUIUtils.createColumn_Getter("Date", "getTimestamp"));
        table.getColumns().add(GUIUtils.createColumn_Getter("Type", "getType"));
    }

    private CashflowModalResult<IncomeType> getInitialResult(Income item) {
        if(item == null)
            return null;

        var initialResult = new CashflowModalResult<IncomeType>();
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
    private final IncomeService incomeService;

    @FXML
    private DatePicker datePickerFrom;

    @FXML
    private DatePicker datePickerTo;

    @FXML
    private Label labelTotal;
}
