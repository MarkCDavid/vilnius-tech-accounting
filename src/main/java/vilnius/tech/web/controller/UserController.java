package vilnius.tech.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.hibernate.User;
import vilnius.tech.hibernate.service.PhysicalUserService;
import vilnius.tech.hibernate.service.UserService;
import vilnius.tech.utils.PasswordUtils;
import vilnius.tech.web.controller.proxy.model.ChangePasswordContext;
import vilnius.tech.web.controller.proxy.model.UserLoginContext;
import vilnius.tech.web.controller.utils.*;

import java.util.Objects;

@RestController
public class UserController {

    @GetMapping(path = "/user/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get(
            @RequestParam(name="take", required = false, defaultValue = "100") Integer take,
            @RequestParam(name="skip", required = false, defaultValue = "0") Integer skip
    ) {
        return JsonResponseUtils.OK(new UserService(HibernateUtils.getSession()).find(take, skip));
    }

    @PostMapping(path = "/user/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody UserLoginContext loginContext) {

        var user = getUser(loginContext.getUsername(), loginContext.getPassword());
        if(user == null)
            return JsonResponseUtils.BAD(Messages.invalidUsernameOrPassword());

        return JsonResponseUtils.OK(new Message("PSEUDO_SESSION_ID"));
    }

    @PostMapping(path = "/user/changePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordContext changePasswordContext) {

        var user = getUser(changePasswordContext.getUsername(), changePasswordContext.getOldPassword());
        if(user == null)
            return JsonResponseUtils.BAD(Messages.invalidUsernameOrPassword());

        var userService = new UserService(HibernateUtils.getSession());
        userService.update(user, changePasswordContext.getNewPassword());

        return JsonResponseUtils.OK(new Message("Password changed successfully."));
    }

    private User getUser(String username, String password) {

        var userService = new UserService(HibernateUtils.getSession());

        var user = userService.find_Username(username);
        if(user == null)
            return null;

        var salt = user.getSalt();
        var hashedPassword = PasswordUtils.generateSecurePassword(password, salt);

        return Objects.equals(hashedPassword, user.getPassword()) ? user : null;
    }

}
