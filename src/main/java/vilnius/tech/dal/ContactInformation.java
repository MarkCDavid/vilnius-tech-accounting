package vilnius.tech.dal;

import vilnius.tech.session.Session;

import java.io.Serializable;

public class ContactInformation extends BaseOid implements Serializable {

    public ContactInformation(Session session) {
        super(session, ContactInformation.class);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private Address address;
    private String email;
    private String phoneNumber;
}
