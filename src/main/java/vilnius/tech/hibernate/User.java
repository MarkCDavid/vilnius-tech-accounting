package vilnius.tech.hibernate;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Integer id;

    private String username;
    private String password;

    private List<FinancialCategory> ownedCategories = new ArrayList<>();
    private List<FinancialCategory> responsibleForCategories = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String username, String password, List<FinancialCategory> ownedCategories, List<FinancialCategory> responsibleForCategories) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.ownedCategories = ownedCategories;
        this.responsibleForCategories = responsibleForCategories;
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

    public List<FinancialCategory> getOwnedCategories() {
        return ownedCategories;
    }

    public void setOwnedCategories(List<FinancialCategory> ownedCategories) {
        this.ownedCategories = ownedCategories;
    }

    public List<FinancialCategory> getResponsibleForCategories() {
        return responsibleForCategories;
    }

    public void setResponsibleForCategories(List<FinancialCategory> responsibleForCategories) {
        this.responsibleForCategories = responsibleForCategories;
    }
}
