package vilnius.tech.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import org.hibernate.Session;
import vilnius.tech.error.DatabaseExceptionPolicy;
import vilnius.tech.hibernate.BaseEntity;
import vilnius.tech.hibernate.User;
import vilnius.tech.utils.GUIUtils;
import vilnius.tech.view.View;

import java.io.IOException;

public abstract class CRUDListController<T extends BaseEntity> extends SessionController {

    public CRUDListController(View previousView, User user, Session session) {
        super(session);
        this.user = user;
        this.previousView = previousView;
    }

    public User getUser() {
        return user;
    }

    protected abstract ObservableList<T> getDataSource();

    protected abstract void onAddNew() throws IOException;
    protected abstract void onUpdate(T item) throws IOException;
    protected abstract void onDelete(T item);

    protected abstract void initializeColumns();

    @FXML
    private void onAddNewCore() throws IOException {
        try {
            onAddNew();
        } catch (Exception e) {
            var error = DatabaseExceptionPolicy.apply(e);
            if(error == null)
                throw e;

            getView().getErrorRouter().route(error);
        }

        updateTable();
    }


    @FXML
    private void onUpdateCore() throws IOException {
        try {
            onUpdate(tableView.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            var error = DatabaseExceptionPolicy.apply(e);
            if(error == null)
                throw e;

            getView().getErrorRouter().route(error);
        }


        updateTable();
    }

    protected void updateTable() {
        this.tableView.setItems(getDataSource());
        GUIUtils.autoResizeColumns(tableView);
    }

    @FXML
    public void onDeleteCore() {
        var item = tableView.getSelectionModel().getSelectedItem();
        if(item == null)
            return;

        try {
            onDelete(item);
        } catch (Exception e) {
            var error = DatabaseExceptionPolicy.apply(e);
            if(error == null)
                throw e;

            getView().getErrorRouter().route(error);
        }

        updateTable();
    }

    @FXML
    public void initialize() {
        initializeColumns();
        updateTable();
    }

    @FXML
    public void onBack() throws IOException {
        previousView.render();
    }

    public TableView<T> getTableView() {
        return tableView;
    }

    @FXML
    private TableView<T> tableView;

    private final User user;
    private final View previousView;
}
