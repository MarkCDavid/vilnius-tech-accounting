package vilnius.tech.view.controller.modal;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import vilnius.tech.view.controller.modal.result.CategoryModalResult;
import vilnius.tech.validation.Validator;
import vilnius.tech.validation.validators.TextLengthValidation;


public class CategoryModalController extends ModalController<CategoryModalResult> {


    public CategoryModalController() {
        this(null);
    }

    public CategoryModalController(CategoryModalResult result) {
        super(result);
    }

    @FXML
    public void initialize() {
        this.validator = new Validator();
        this.validator.register(0, new TextLengthValidation(textFieldCategoryName, 3, 32));

        if(getModalResult() == null)
            return;

        textFieldCategoryName.setText(getModalResult().getName());
    }

    @Override
    public boolean constructModalResult() {
        if(!validator.validate())
            return false;

        getModalResult().setName(textFieldCategoryName.getText());

        return true;
    }

    @Override
    protected CategoryModalResult createModalResult() {
        return new CategoryModalResult();
    }

    private Validator validator;

    @FXML
    TextField textFieldCategoryName;
}
