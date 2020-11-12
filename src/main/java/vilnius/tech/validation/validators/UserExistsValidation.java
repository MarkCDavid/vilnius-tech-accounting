package vilnius.tech.validation.validators;

import javafx.scene.control.TextInputControl;
import org.hibernate.Session;
import vilnius.tech.hibernate.service.UserService;
import vilnius.tech.validation.Validation;
import vilnius.tech.validation.ValidationResult;

public class UserExistsValidation implements Validation {

    public UserExistsValidation(Session session, TextInputControl usernameControl) {
        this.session = session;
        this.usernameControl = usernameControl;
        this.userController = new UserService(session);
    }

    @Override
    public ValidationResult validate() {
        String username = usernameControl.getText();
        var user = userController.find_Username(username);

        if(user != null) {
            return ValidationResult.BAD(String.format("User with username %s already exists.", username));
        }
        return ValidationResult.OK();
    }

    private final Session session;
    private final TextInputControl usernameControl;
    private final UserService userController;
}
