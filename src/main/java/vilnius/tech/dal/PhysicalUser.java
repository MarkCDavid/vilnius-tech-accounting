package vilnius.tech.dal;

import vilnius.tech.session.Session;

import java.io.Serializable;

public final class PhysicalUser extends User implements Serializable {

    public PhysicalUser(Session session) {
        super(session);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public ContactInformation getContactInfo() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    private String name;
    private String surname;
    private ContactInformation contactInformation;

    @Override
    public String toString() {
        return getUsername();
    }
}
