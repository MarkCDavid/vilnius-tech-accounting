package vilnius.tech.hibernate;

import java.util.List;

public class JuridicalUser extends User {

    private String name;
    private Address address;
    private PhysicalUser contactUser;

    public JuridicalUser(Integer id, String username, String password, List<FinancialCategory> ownedCategories, List<FinancialCategory> responsibleForCategories, String name, Address address, PhysicalUser contactUser) {
        super(id, username, password, ownedCategories, responsibleForCategories);
        this.name = name;
        this.address = address;
        this.contactUser = contactUser;
    }

    public JuridicalUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PhysicalUser getContactUser() {
        return contactUser;
    }

    public void setContactUser(PhysicalUser contactUser) {
        this.contactUser = contactUser;
    }
}
