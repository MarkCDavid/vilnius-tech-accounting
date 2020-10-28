package vilnius.tech.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vilnius.tech.dal.ExpenseType;
import vilnius.tech.dal.IncomeType;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.view.View;

public class ExpenseTypeCRUDListController extends CRUDListController<ExpenseType> {

    public ExpenseTypeCRUDListController(View previousView, User user, Session session) {
        super(previousView, user, session);
    }

    @Override
    protected ObservableList<ExpenseType> getDataSource() {
        return FXCollections.observableArrayList(getSession().query(ExpenseType.class));
    }

    @Override
    protected void onAddNew() {
        // START MODAL, GET VALUES, CREATE IF MODAL SUCC
    }

    @Override
    protected void onUpdate(ExpenseType item) {
        // START MODAL, GET VALUES, UPDATE IF MODAL SUCC
    }
}
