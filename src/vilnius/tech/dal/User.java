package vilnius.tech.dal;

import java.io.Serializable;

public abstract class User extends BaseOid implements Serializable {

    public User(Session session) {
        super(session);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;
}
