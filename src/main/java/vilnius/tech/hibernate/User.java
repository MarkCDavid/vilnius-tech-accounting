package vilnius.tech.hibernate;

import com.google.gson.annotations.Expose;
import vilnius.tech.utils.PasswordUtils;

import java.util.HashSet;
import java.util.Set;

public class User implements BaseEntity  {

    @Expose
    private Integer id;

    @Expose
    private String username;

    private String password;

    private String salt;

    private Set<FinancialCategory> ownedCategories;
    private Set<FinancialCategory> responsibleForCategories;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<FinancialCategory> getOwnedCategories() {
        return ownedCategories;
    }

    public void setOwnedCategories(Set<FinancialCategory> ownedCategories) {
        this.ownedCategories = ownedCategories;
    }

    public Set<FinancialCategory> getResponsibleForCategories() {
        return responsibleForCategories;
    }

    public void setResponsibleForCategories(Set<FinancialCategory> responsibleForCategories) {
        this.responsibleForCategories = responsibleForCategories;
    }

    @Override
    public String toString() {
        return username;
    }
}
