package vilnius.tech.dal;

import java.io.Serializable;

public class ContactInformation extends BaseOid implements Serializable {

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

    @Override
    public String toString() {
        return String.format("%s%nEmail: %s%nPhone Number: %s", address, email, phoneNumber);
    }

    @Override
    public String toShortString() {
        return String.format("%s, %s, %s", address.toShortString(), email, phoneNumber);
    }
}
