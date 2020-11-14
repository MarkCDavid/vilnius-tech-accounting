package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.FinancialCategory;
import vilnius.tech.hibernate.User;
import vilnius.tech.utils.PasswordUtils;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class UserService extends HibernateService<User> {

    public UserService(Session session) {
        super(User.class, session);
    }

    public User create(String username, String password) {
        var user = new User();
        user.setUsername(username);
        user.setSalt(PasswordUtils.getSalt(128));
        user.setPassword(PasswordUtils.generateSecurePassword(password, user.getSalt()));
        return update(user);
    }

    public User find_Username(String username) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();
            var query = entityManager.createQuery(queryBuilder.getCriteriaQuery().where(
                    queryBuilder.getBuilder().like(queryBuilder.getRoot().get("username"), username)
            ));
            var results = query.getResultList();
            if(results.isEmpty())
                return null;

            return results.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> find_NotResponsibleFor(FinancialCategory category) {
        return find();
    }

    public List<User> find_ResponsibleFor(FinancialCategory category) {
        try (var entityManager = getEntityManager()) {
            return find().stream().filter(user -> user.getResponsibleForCategories().contains(category)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
