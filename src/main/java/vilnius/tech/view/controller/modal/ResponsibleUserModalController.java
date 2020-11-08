package vilnius.tech.view.controller.modal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vilnius.tech.view.controller.modal.result.ChoiceBoxModalResult;
import vilnius.tech.dal.FinancialCategory;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;

public class ResponsibleUserModalController extends ChoiceBoxModalController<User> {

    public ResponsibleUserModalController(Session session, FinancialCategory category) {
        this(session, category, null);
    }

    public ResponsibleUserModalController(Session session, FinancialCategory category, ChoiceBoxModalResult<User> result) {
        super(session, result);
        this.category = category;
    }

    @Override
    protected ObservableList<User> getDataSource() {
        return FXCollections.observableArrayList(
                getSession().query(User.class, user -> !category.getResponsibleUsers().contains(user))
        );
    }

    private final FinancialCategory category;

}
