package vilnius.tech.validation.validators;

import javafx.scene.control.TextInputControl;
import vilnius.tech.dal.User;
import vilnius.tech.session.Session;
import vilnius.tech.utils.UsersUtils;
import vilnius.tech.validation.Validation;
import vilnius.tech.validation.ValidationResult;

import java.util.List;

public class UserExistsValidation implements Validation {

    public UserExistsValidation(Session session, TextInputControl usernameControl) {
        this.session = session;
        this.usernameControl = usernameControl;
    }

    @Override
    public ValidationResult validate() {
        String username = usernameControl.getText();
        List<User> users = session.query(User.class, user -> UsersUtils.matchUsername(user, username));

        if(users.size() > 0) {
            return ValidationResult.BAD(String.format("User with username %s already exists.", username));
        }
        return ValidationResult.OK();
    }

    private final Session session;
    private final TextInputControl usernameControl;
}
