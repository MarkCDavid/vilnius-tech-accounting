package vilnius.tech.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vilnius.tech.dal.IncomeType;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.view.View;

public class IncomeTypeCRUDListController extends CRUDListController<IncomeType> {

    public IncomeTypeCRUDListController(View previousView, User user, Session session) {
        super(previousView, user, session);
    }

    @Override
    protected ObservableList<IncomeType> getDataSource() {
        return FXCollections.observableArrayList(getSession().query(IncomeType.class));
    }

    @Override
    protected void onAddNew() {
        // START MODAL, GET VALUES, CREATE IF MODAL SUCC
    }

    @Override
    protected void onUpdate(IncomeType item) {
        // START MODAL, GET VALUES, UPDATE IF MODAL SUCC
    }
}
