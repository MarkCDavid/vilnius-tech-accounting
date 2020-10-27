package vilnius.tech.validation.validators;

import javafx.scene.control.TextInputControl;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.utils.UsersUtils;
import vilnius.tech.validation.Validation;
import vilnius.tech.validation.ValidationResult;

import java.util.List;

public class UserValidValidation implements Validation {

    public UserValidValidation(Session session, TextInputControl usernameControl, TextInputControl passwordControl) {
        this.session = session;
        this.usernameControl = usernameControl;
        this.passwordControl = passwordControl;
    }

    @Override
    public ValidationResult validate() {
        String username = usernameControl.getText();
        String password = passwordControl.getText();
        List<User> users = session.query(User.class, user -> UsersUtils.matchCredentials(user, username, password));

        if(users.size() != 1) {
            return ValidationResult.BAD("Invalid username or password!");
        }
        return ValidationResult.OK();
    }

    private final Session session;
    private final TextInputControl usernameControl;
    private final TextInputControl passwordControl;
}
