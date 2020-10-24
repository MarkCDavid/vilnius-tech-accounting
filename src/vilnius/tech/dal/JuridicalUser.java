package vilnius.tech.dal;

import vilnius.tech.session.Session;

import java.io.Serializable;

public final class JuridicalUser extends User implements Serializable {

    public JuridicalUser(Session session) {
        super(session);
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

    private String name;
    private Address address;
    private PhysicalUser contactUser;

}
