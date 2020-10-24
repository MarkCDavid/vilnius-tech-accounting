package vilnius.tech.dal;

import vilnius.tech.session.Session;

import java.io.Serializable;

public class Company extends BaseOid implements Serializable {

    public Company(Session session) {
        super(session, Company.class);
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
}
