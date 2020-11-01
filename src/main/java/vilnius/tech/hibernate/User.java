package vilnius.tech.hibernate;

import java.util.HashSet;
import java.util.Set;

public class User {

    private Integer id;

    private String username;
    private String password;

    private Set<FinancialCategory> ownedCategories = new HashSet<>();
    private Set<FinancialCategory> responsibleForCategories = new HashSet<>();

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
}
