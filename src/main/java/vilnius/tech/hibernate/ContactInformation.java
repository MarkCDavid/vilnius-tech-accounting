package vilnius.tech.hibernate;

import java.util.ArrayList;
import java.util.List;

public class ContactInformation {

    private Integer id;

    private Address address;

    public ContactInformation(Integer id, Address address, String email, String phoneNumber, List<PhysicalUser> physicalUsers) {
        this.id = id;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.physicalUsers = physicalUsers;
    }

    public ContactInformation() {
    }

    private String email;

    private String phoneNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<PhysicalUser> getPhysicalUsers() {
        return physicalUsers;
    }

    public void setPhysicalUsers(List<PhysicalUser> physicalUsers) {
        this.physicalUsers = physicalUsers;
    }

    private List<PhysicalUser> physicalUsers = new ArrayList<>();
}
