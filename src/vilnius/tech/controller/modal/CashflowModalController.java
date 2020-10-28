package vilnius.tech.controller.modal;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import vilnius.tech.controller.modal.result.CashflowModalResult;
import vilnius.tech.session.Session;
import vilnius.tech.validation.Validator;
import vilnius.tech.validation.validators.ChoiceBoxNotNullValidation;
import vilnius.tech.validation.validators.TextLongFormatValidation;


public abstract class CashflowModalController<T> extends ModalController<CashflowModalResult<T>> {


    public CashflowModalController(Session session) {
        this(session, null);
    }

    public CashflowModalController(Session session, CashflowModalResult<T> result) {
        super(result);
        this.session = session;
    }

    protected CashflowModalResult<T> createModalResult() {
        return new CashflowModalResult<>();
    }

    protected abstract ObservableList<T> getTypes();

    @FXML
    public void initialize() {
        this.validator = new Validator();
        this.validator.register(0, new ChoiceBoxNotNullValidation<>(choiceBoxTypes));
        this.validator.register(0, new TextLongFormatValidation(textFieldSum));

        choiceBoxTypes.setItems(getTypes());

        if(getModalResult() == null)
            return;

        choiceBoxTypes.setValue(getModalResult().getType());
        textFieldSum.setText(getModalResult().getSum().toString());
    }

    @Override
    public boolean constructModalResult() {
        if(!validator.validate())
            return false;

        getModalResult().setType(choiceBoxTypes.getValue());
        getModalResult().setSum(Long.parseLong(textFieldSum.getText()));

        return true;
    }

    private Validator validator;

    public Session getSession() {
        return session;
    }

    private final Session session;

    @FXML
    ChoiceBox<T> choiceBoxTypes;

    @FXML
    TextField textFieldSum;
}
