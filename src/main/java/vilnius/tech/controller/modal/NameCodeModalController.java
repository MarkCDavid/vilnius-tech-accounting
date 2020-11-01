package vilnius.tech.controller.modal;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import vilnius.tech.controller.modal.result.NameCodeModalResult;
import vilnius.tech.validation.Validator;
import vilnius.tech.validation.validators.TextLengthValidation;


public class NameCodeModalController extends ModalController<NameCodeModalResult> {


    public NameCodeModalController() {
        this(null);
    }

    public NameCodeModalController(NameCodeModalResult result) {
        super(result);
    }

    @FXML
    public void initialize() {
        this.validator = new Validator();
        this.validator.register(0, new TextLengthValidation(textFieldCode, 2, 8));
        this.validator.register(0, new TextLengthValidation(textFieldName, 2, 32));

        if(getModalResult() == null)
            return;

        textFieldName.setText(getModalResult().getName());
        textFieldCode.setText(getModalResult().getCode());
    }

    @Override
    public boolean constructModalResult() {
        if(!validator.validate())
            return false;

        getModalResult().setName(textFieldName.getText());
        getModalResult().setCode(textFieldCode.getText());

        return true;
    }

    @Override
    protected NameCodeModalResult createModalResult() {
        return new NameCodeModalResult();
    }

    private Validator validator;

    @FXML
    TextField textFieldName;

    @FXML
    TextField textFieldCode;
}
