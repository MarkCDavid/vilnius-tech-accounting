package vilnius.tech.controller.modal;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import vilnius.tech.controller.modal.result.ChoiceBoxModalResult;
import vilnius.tech.controller.modal.result.NameCodeModalResult;
import vilnius.tech.dal.FinancialCategory;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.validation.Validator;
import vilnius.tech.validation.validators.TextLengthValidation;


public abstract class ChoiceBoxModalController<T> extends ModalController<ChoiceBoxModalResult<T>> {

    public ChoiceBoxModalController(Session session) {
        this(session, null);
    }

    public Session getSession() {
        return session;
    }

    public ChoiceBoxModalController(Session session, ChoiceBoxModalResult<T> result) {
        super(result);
        this.session = session;
    }

    protected abstract ObservableList<T> getDataSource();

    @FXML
    public void initialize() {
        choiceBoxItems.setItems(getDataSource());

        if(getModalResult() == null)
            return;

        choiceBoxItems.setValue(getModalResult().getSelectedItem());
    }

    @Override
    public boolean constructModalResult() {
        getModalResult().setSelectedItem(choiceBoxItems.getValue());

        return true;
    }

    @Override
    protected ChoiceBoxModalResult<T> createModalResult() {
        return new ChoiceBoxModalResult<>();
    }

    @FXML
    ChoiceBox<T> choiceBoxItems;

    private final Session session;

}
