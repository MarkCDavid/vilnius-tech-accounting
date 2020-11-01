package vilnius.tech.hibernate;

import java.util.ArrayList;
import java.util.List;

public class Address {


    private Integer id;

    private City city;

    private String street;
    private String postal;
    private List<ContactInformation> contactInformations = new ArrayList<>();
    private List<JuridicalUser> juridicalUsersAtAddress = new ArrayList<>();

    public Address(Integer id, City city, String street, String postal, List<ContactInformation> contactInformations, List<JuridicalUser> juridicalUsersAtAddress) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.postal = postal;
        this.contactInformations = contactInformations;
        this.juridicalUsersAtAddress = juridicalUsersAtAddress;
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

    public List<ContactInformation> getContactInformations() {
        return contactInformations;
    }

    public void setContactInformations(List<ContactInformation> contactInformations) {
        this.contactInformations = contactInformations;
    }

    public List<JuridicalUser> getJuridicalUsersAtAddress() {
        return juridicalUsersAtAddress;
    }

    public void setJuridicalUsersAtAddress(List<JuridicalUser> juridicalUsersAtAddress) {
        this.juridicalUsersAtAddress = juridicalUsersAtAddress;
    }

}
