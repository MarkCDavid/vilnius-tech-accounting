package vilnius.tech.dal;

import java.io.Serializable;

public class Company extends BaseOid implements Serializable {

    public Company(Session session) {
        super(session);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    private String name;
    private ContactInformation contactInformation;

    @Override
    public String toString() {
        if(isDeleted()) return "<deleted>";
        return formatValue(name, "Name") +
               formatReference(contactInformation, "Contact Information");
    }

    @Override
    public String toShortString() {
        if(isDeleted()) return "<deleted>";
        return String.format("Company Name: %s%n", name);
    }
}
