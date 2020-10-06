package vilnius.tech.dal;

import java.io.Serializable;

public final class JuridicalUser extends User implements Serializable {

    public static final String CODE = "juridical";

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

    @Override
    public String toString() {
        return String.format("%s - %s%n%s%nContact:%n%s", getUsername(), name, address, contactUser);
    }

    @Override
    public String toShortString() {
        return String.format("%s - %s", getUsername(), name);
    }
}
