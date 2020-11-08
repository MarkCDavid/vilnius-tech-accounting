package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.ContactInformation;
import vilnius.tech.hibernate.PhysicalUser;
import vilnius.tech.hibernate.User;
import vilnius.tech.session.HibernateController;
import vilnius.tech.utils.PasswordUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public class UserController extends HibernateController<User> {

    public UserController(Session session) {
        super(User.class, session);
    }

    public User create(String username, String password) {
        var user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return update(user);
    }

    public User find_Username(String username) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = getQueryBuilder().begin();
            var query = entityManager.createQuery(queryBuilder.getCriteriaQuery().where(
                    queryBuilder.getBuilder().like(queryBuilder.getRoot().get("username"), username)
            ));
            return query.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
