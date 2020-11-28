package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.*;
import vilnius.tech.hibernate.utils.CRUDUtils;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

public class FinancialCategoryService extends HibernateService<FinancialCategory> {

    public FinancialCategoryService(Session session) {
        super(FinancialCategory.class, session);
    }

    public FinancialCategory create(FinancialCategory parent, String name, User owner) {
        return update(new FinancialCategory(), parent, name, owner);
    }


    public FinancialCategory update(FinancialCategory financialCategory, FinancialCategory parent, String name, User owner) {
        try (var entityManager = getEntityManager();
             var service = new FinancialCategoryService(getSession())
        ) {
            financialCategory.setParent(parent);
            financialCategory.setName(name);
            financialCategory.setOwner(owner);
            return service.update(financialCategory);
        }
    }

    public FinancialCategory add_ResponsibleUser(FinancialCategory category, User responsible) {
        try (var entityManager = getEntityManager();
            var service = new FinancialCategoryService(getSession())) {
            category.getResponsibleUsers().add(responsible);
            return service.update(category);
        }
    }

    public FinancialCategory remove_ResponsibleUser(FinancialCategory category, User responsible) {
        try (var entityManager = getEntityManager();
             var financialCategoryService = new FinancialCategoryService(getSession());
             var userService = new UserService(getSession())) {
            CRUDUtils.remove(category.getResponsibleUsers(), responsible);
            CRUDUtils.remove(responsible.getResponsibleForCategories(), category);

            userService.update(responsible);
            return financialCategoryService.update(category);
        }
    }

    public List<FinancialCategory> find_User(User user) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();

            var criteriaQuery = queryBuilder.getCriteriaQuery();
            var root = queryBuilder.getRoot();
            var builder = queryBuilder.getBuilder();

            var join = root.join("responsibleUsers", JoinType.LEFT);
            join.on(
                    builder.equal(join.get("id"), user.getId())
            );

            var query = entityManager.createQuery(
                    criteriaQuery.where(
                            builder.or(
                                    builder.equal(root.get("owner"), user),
                                    builder.equal(join.get("id"), user.getId())
                            )
                    ).orderBy(builder.asc(root.get("id"))).distinct(true)
            );
            return query.getResultList();
        }
    }

    public List<FinancialCategory> find_Parent(FinancialCategory financialCategory) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();

            var criteriaQuery = queryBuilder.getCriteriaQuery();
            var root = queryBuilder.getRoot();
            var builder = queryBuilder.getBuilder();

            var query = entityManager.createQuery(
                criteriaQuery.where(
                    builder.equal(root.get("parent"), financialCategory)
                ).distinct(true)
            );
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
