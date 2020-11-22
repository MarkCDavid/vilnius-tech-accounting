package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.ContactInformation;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.hibernate.User;
import vilnius.tech.utils.PasswordUtils;

public class PhysicalUserService extends HibernateService<PhysicalUser> {

    public PhysicalUserService(Session session) {
        super(PhysicalUser.class, session);
    }

    public PhysicalUser create(String username, String password, String name, String surname, ContactInformation contactInformation) {
        var physicalUser = new PhysicalUser();
        physicalUser.setUsername(username);
        physicalUser.setSalt(PasswordUtils.getSalt(128));
        physicalUser.setPassword(PasswordUtils.generateSecurePassword(password, physicalUser.getSalt()));
        physicalUser.setName(name);
        physicalUser.setSurname(surname);
        physicalUser.setContactInformation(contactInformation);
        return update(physicalUser);
    }

    public PhysicalUser find_Username(String username) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();
            var query = entityManager.createQuery(queryBuilder.getCriteriaQuery().where(
                    queryBuilder.getBuilder().like(queryBuilder.getRoot().get("username"), username)
            ));
            var results = query.getResultList();
            if (results.isEmpty())
                return null;

            return results.get(0);
        }
    }

}
