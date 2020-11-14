package vilnius.tech.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.service.FinancialCategoryService;
import vilnius.tech.hibernate.service.SetService;
import vilnius.tech.hibernate.service.UserService;
import vilnius.tech.utils.GUIUtils;
import vilnius.tech.view.controller.modal.ResponsibleUserModalController;
import vilnius.tech.view.Modal;
import vilnius.tech.view.View;
import vilnius.tech.view.controller.modal.result.ChoiceBoxModalResult;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class ResponsibleUserCRUDListController extends CRUDListController<User> {

    private final FinancialCategoryService financialCategoryService;
    private final SetService<FinancialCategory, User> setService;


    public ResponsibleUserCRUDListController(View previousView, FinancialCategory category, User user, Session session) {
        super(previousView, user, session);
        this.financialCategoryService = new FinancialCategoryService(session);
        this.category = financialCategoryService.find(category);
        this.setService = new SetService<>(this.category, this.category.getResponsibleUsers(), session);
    }

    @Override
    protected ObservableList<User> getDataSource() {
        return FXCollections.observableArrayList(category.getResponsibleUsers());
    }

    @Override
    protected void onAddNew() throws IOException {
        var modal = new Modal<>(new ResponsibleUserModalController(getSession(), category), getView(), "Add new Responsible User", "dropdownModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        setService.add(result.getSelectedItem());
    }

    @Override
    protected void onUpdate(User item) throws IOException {
        if(item == null)
            return;

        var modal = new Modal<>(new ResponsibleUserModalController(getSession(), category, getInitialResult(item)), getView(), "Update Responsible User", "dropdownModal.fxml");
        var result = modal.render();
        if(result == null)
            return;

        setService.remove(item);
        setService.add(result.getSelectedItem());
    }

    @Override
    protected void onDelete(User item) {
        setService.remove(item);
    }

    @Override
    protected void initializeColumns() {
        var table = getTableView();
        table.getColumns().add(GUIUtils.createColumn("Username", "username"));
    }

    private ChoiceBoxModalResult<User> getInitialResult(User item) {
        if(item == null)
            return null;

        var initialResult = new ChoiceBoxModalResult<User>();
        initialResult.setSelectedItem(item);
        return initialResult;
    }

    private final FinancialCategory category;
}
