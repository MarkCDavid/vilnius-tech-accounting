package vilnius.tech.hibernate;

import java.util.ArrayList;
import java.util.List;

public class PhysicalUser extends User {

    private String name;
    private String surname;

    private ContactInformation contactInformation;
    private List<JuridicalUser> juridicalContact = new ArrayList<>();

    public PhysicalUser() {
    }

    public PhysicalUser(Integer id, String username, String password, List<FinancialCategory> ownedCategories, List<FinancialCategory> responsibleForCategories, String name, String surname, ContactInformation contactInformation, List<JuridicalUser> juridicalContact) {
        super(id, username, password, ownedCategories, responsibleForCategories);
        this.name = name;
        this.surname = surname;
        this.contactInformation = contactInformation;
        this.juridicalContact = juridicalContact;
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

    public List<JuridicalUser> getJuridicalContact() {
        return juridicalContact;
    }

    public void setJuridicalContact(List<JuridicalUser> juridicalContact) {
        this.juridicalContact = juridicalContact;
    }
}
