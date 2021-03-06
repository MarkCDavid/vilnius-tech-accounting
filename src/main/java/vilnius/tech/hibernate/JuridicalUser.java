package vilnius.tech.hibernate;

import com.google.gson.annotations.Expose;

public class JuridicalUser extends User {

    @Expose
    private String name;

    @Expose
    private Address address;

    @Expose
    private PhysicalUser contactUser;

    public JuridicalUser(String username, String password, String name, Address address, PhysicalUser contactUser) {
        super( username, password);
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
