//package vilnius.tech.controller;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.ListView;
//import vilnius.tech.dal.BaseOid;
//import vilnius.tech.dal.User;
//import vilnius.tech.session.Session;
//import vilnius.tech.view.View;
//
//import java.io.IOException;
//
//public abstract class CRUDListController<T extends BaseOid> extends SessionController {
//
//    public CRUDListController(View previousView, User user, Session session) {
//        super(session);
//        this.user = user;
//        this.previousView = previousView;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    protected abstract ObservableList<T> getDataSource();
//    protected abstract void onAddNew() throws IOException;
//    protected abstract void onUpdate(T item) throws IOException;
//    protected void onDelete(T item) throws IOException {
//        var baseOid = (BaseOid)item;
//        baseOid.delete();
//    }
//
//    @FXML
//    private void onAddNewCore() throws IOException {
//        onAddNew();
//        this.listCrud.setItems(getDataSource());
//    }
//
//
//    @FXML
//    private void onUpdateCore() throws IOException {
//        onUpdate(listCrud.getSelectionModel().getSelectedItem());
//        this.listCrud.setItems(getDataSource());
//    }
//
//    @FXML
//    public void onDeleteCore() throws IOException {
//        var item = listCrud.getSelectionModel().getSelectedItem();
//        if(item == null)
//            return;
//
//        onDelete(item);
//
//        this.listCrud.setItems(getDataSource());
//    }
//
//    @FXML
//    public void initialize() {
//        this.listCrud.setItems(getDataSource());
//    }
//
//    @FXML
//    public void onBack() throws IOException {
//        previousView.render();
//    }
//
//    @FXML
//    ListView<T> listCrud;
//
//    private final User user;
//    private final View previousView;
//}
