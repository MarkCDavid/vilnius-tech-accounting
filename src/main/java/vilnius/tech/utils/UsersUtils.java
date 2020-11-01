package vilnius.tech.utils;

import vilnius.tech.dal.User;

import java.util.Objects;

public class UsersUtils {

    public static boolean matchCredentials(User user, String username, String password) {
        return matchUsername(user, username) && matchPassword(user, password);
    }

    public static boolean matchPassword(User user, String value) {
        return Objects.equals(user.getPassword(), value);
    }

    public static boolean matchUsername(User user, String value) {
        return Objects.equals(user.getUsername(), value);
    }

    private UsersUtils() {

    }
}
