package vilnius.tech.validation.validators;

import javafx.scene.control.TextInputControl;
import org.hibernate.Session;
import vilnius.tech.hibernate.service.UserService;
import vilnius.tech.utils.PasswordUtils;
import vilnius.tech.validation.Validation;
import vilnius.tech.validation.ValidationResult;

public class UserValidValidation implements Validation {

    public UserValidValidation(Session session, TextInputControl usernameControl, TextInputControl passwordControl) {
        this.usernameControl = usernameControl;
        this.passwordControl = passwordControl;
        this.userController = new UserService(session);
    }

    @Override
    public ValidationResult validate() {
        var username = usernameControl.getText();

        var user = userController.find_Username(username);

        if(user == null) {
            return ValidationResult.BAD("Invalid username or password!");
        }

        var valid = PasswordUtils.verifyUserPassword(passwordControl.getText(), user.getPassword(), user.getSalt());

        if(!valid) {
            return ValidationResult.BAD("Invalid username or password!");
        }
        return ValidationResult.OK();
    }

    private final TextInputControl usernameControl;
    private final TextInputControl passwordControl;
    private final UserService userController;
}
