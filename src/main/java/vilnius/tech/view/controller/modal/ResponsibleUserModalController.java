package vilnius.tech.view.controller.modal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.service.UserService;
import vilnius.tech.view.controller.modal.result.ChoiceBoxModalResult;

public class ResponsibleUserModalController extends ChoiceBoxModalController<User> {

    public ResponsibleUserModalController(Session session, FinancialCategory category) {
        this(session, category, null);
    }

    public ResponsibleUserModalController(Session session, FinancialCategory category, ChoiceBoxModalResult<User> result) {
        super(session, result);
        this.category = category;
        this.controller = new UserService(session);
    }

    @Override
    protected ObservableList<User> getDataSource() {
        return FXCollections.observableArrayList(controller.find_NotResponsibleFor(category));
    }

    private final FinancialCategory category;
    private final UserService controller;

}
