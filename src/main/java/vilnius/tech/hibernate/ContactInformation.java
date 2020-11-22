package vilnius.tech.hibernate;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

public class ContactInformation implements BaseEntity  {

    @Expose
    private Integer id;

    @Expose
    private Address address;

    private Set<PhysicalUser> physicalUsers = new HashSet<>();

    public ContactInformation(Address address, String email, String phoneNumber) {
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public Set<PhysicalUser> getPhysicalUsers() {
        return physicalUsers;
    }

    public void setPhysicalUsers(Set<PhysicalUser> physicalUsers) {
        this.physicalUsers = physicalUsers;
    }
}
