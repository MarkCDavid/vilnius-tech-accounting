package vilnius.tech.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.view.View;

import java.io.IOException;

public class MainController extends SessionController {
    
    private static final String LOGGED_IN_TEMPLATE = "Logged in as: %s";

    public MainController(User user, Session session) {
        super(session);
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    @FXML
    public void initialize() {
        labelLoggedInAs.setText(String.format(LOGGED_IN_TEMPLATE, user.getUsername()));
    }

    public void onManageExpenseTypes() throws IOException {
        var controller = new ExpenseTypeCRUDListController(getView(), getUser(), getSession());
        new View(controller, getStage(), "Expense Types", "listcrud.fxml").render();
    }

    public void onManageIncomeTypes() throws IOException {
        var controller = new IncomeTypeCRUDListController(getView(), getUser(), getSession());
        new View(controller, getStage(), "Income Types", "listcrud.fxml").render();
    }

    public void onManageTransactionCategories() throws IOException {
        var controller = new CategoryController(getView(), getUser(), getSession());
        new View(controller, getStage(), "Categories", "categories.fxml").render();
    }

    public void onLogOut() throws IOException {
        var controller = new GatewayController(getSession());
        new View(controller, getStage(), "Gateway", "gateway.fxml").render();
    }

    @FXML
    Label labelLoggedInAs;
    private final User user;
}
