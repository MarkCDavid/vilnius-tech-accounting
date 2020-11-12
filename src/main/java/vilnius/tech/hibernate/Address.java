package vilnius.tech.hibernate;

import org.hibernate.collection.internal.PersistentSet;

import java.util.HashSet;
import java.util.Set;

public class Address implements BaseEntity {

    private Integer id;

    private City city;

    private String street;
    private String postal;
    private Set<ContactInformation> contactInformations = new HashSet<>();
    private Set<JuridicalUser> juridicalUsersAtAddress = new HashSet<>();

    public Address(City city, String street, String postal) {
        this.city = city;
        this.street = street;
        this.postal = postal;
    }

    public Address() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Set<ContactInformation> getContactInformations() {
        return contactInformations;
    }

    public void setContactInformations(Set<ContactInformation> contactInformations) {
        this.contactInformations = contactInformations;
    }

    public Set<JuridicalUser> getJuridicalUsersAtAddress() {
        return juridicalUsersAtAddress;
    }

    public void setJuridicalUsersAtAddress(Set<JuridicalUser> juridicalUsersAtAddress) {
        this.juridicalUsersAtAddress = juridicalUsersAtAddress;
    }

}
