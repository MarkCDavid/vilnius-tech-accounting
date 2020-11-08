//package vilnius.tech.view.controller;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import vilnius.tech.view.controller.crud.ExpenseTypeCU;
//import vilnius.tech.view.controller.modal.NameCodeModalController;
//import vilnius.tech.view.controller.modal.ResponsibleUserModalController;
//import vilnius.tech.view.controller.modal.result.ChoiceBoxModalResult;
//import vilnius.tech.view.controller.modal.result.NameCodeModalResult;
//import vilnius.tech.dal.ExpenseType;
//import vilnius.tech.dal.FinancialCategory;
//import vilnius.tech.dal.User;
//import vilnius.tech.session.Session;
//import vilnius.tech.view.Modal;
//import vilnius.tech.view.View;
//
//import java.io.IOException;
//
//public class ResponsibleUserCRUDListController extends CRUDListController<User> {
//
//    public ResponsibleUserCRUDListController(View previousView, FinancialCategory category, User user, Session session) {
//        super(previousView, user, session);
//        this.category = category;
//    }
//
//    @Override
//    protected ObservableList<User> getDataSource() {
//        return FXCollections.observableArrayList(category.getResponsibleUsers());
//    }
//
//    @Override
//    protected void onAddNew() throws IOException {
//        var modal = new Modal<>(new ResponsibleUserModalController(getSession(), category), getView(), "Add new Responsible User", "dropdownModal.fxml");
//        var result = modal.render();
//        if(result == null)
//            return;
//
//        category.getResponsibleUsers().add(result.getSelectedItem());
//    }
//
//    @Override
//    protected void onUpdate(User item) throws IOException {
//        if(item == null)
//            return;
//
//        var modal = new Modal<>(new ResponsibleUserModalController(getSession(), category, getInitialResult(item)), getView(), "Update Responsible User", "dropdownModal.fxml");
//        var result = modal.render();
//        if(result == null)
//            return;
//
//        category.getResponsibleUsers().remove(item);
//        category.getResponsibleUsers().add(result.getSelectedItem());
//    }
//
//    @Override
//    protected void onDelete(User item) throws IOException {
//        category.getResponsibleUsers().remove(item);
//    }
//
//    private ChoiceBoxModalResult<User> getInitialResult(User item) {
//        if(item == null)
//            return null;
//
//        var initialResult = new ChoiceBoxModalResult<User>();
//        initialResult.setSelectedItem(item);
//        return initialResult;
//    }
//
//    private final FinancialCategory category;
//}
