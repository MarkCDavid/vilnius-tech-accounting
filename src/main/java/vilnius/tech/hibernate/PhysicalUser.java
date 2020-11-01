package vilnius.tech.hibernate;

import java.util.HashSet;
import java.util.Set;

public class PhysicalUser extends User {

    private String name;
    private String surname;

    private ContactInformation contactInformation;
    private Set<JuridicalUser> juridicalContact = new HashSet<>();

    public PhysicalUser() {
    }

    public PhysicalUser(String username, String password, String name, String surname, ContactInformation contactInformation) {
        super(username, password);
        this.name = name;
        this.surname = surname;
        this.contactInformation = contactInformation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Set<JuridicalUser> getJuridicalContact() {
        return juridicalContact;
    }

    public void setJuridicalContact(Set<JuridicalUser> juridicalContact) {
        this.juridicalContact = juridicalContact;
    }
}
