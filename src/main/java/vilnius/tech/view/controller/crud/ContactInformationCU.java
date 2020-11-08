package vilnius.tech.view.controller.crud;

import vilnius.tech.dal.City;
import vilnius.tech.dal.ContactInformation;
import vilnius.tech.session.Session;

public class ContactInformationCU {

    public static ContactInformation create(Session session, City city, String street, String postal, String phoneNumber, String email) {
        var contactInformation = new ContactInformation(session);
        update(contactInformation, city, street, postal, phoneNumber, email);
        return contactInformation;
    }

    public static void update(ContactInformation contactInformation, City city, String street, String postal, String phoneNumber, String email) {
        contactInformation.setAddress(AddressCU.create(contactInformation.getSession(), city, street, postal));
        contactInformation.setPhoneNumber(phoneNumber);
        contactInformation.setEmail(email);
    }

    private ContactInformationCU() { }
}
