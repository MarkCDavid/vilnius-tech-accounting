package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.Address;
import vilnius.tech.hibernate.ContactInformation;

public class ContactInformationService extends HibernateService<ContactInformation> {

    public ContactInformationService(Session session) {
        super(ContactInformation.class, session);
    }

    public ContactInformation create(Address address, String email, String phoneNumber) {
        var contactInformation = new ContactInformation();
        contactInformation.setAddress(address);
        contactInformation.setEmail(email);
        contactInformation.setPhoneNumber(phoneNumber);
        return update(contactInformation);
    }

}
