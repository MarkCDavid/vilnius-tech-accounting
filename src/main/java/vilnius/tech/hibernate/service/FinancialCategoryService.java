package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.*;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

public class FinancialCategoryService extends HibernateService<FinancialCategory> {

    public FinancialCategoryService(Session session) {
        super(FinancialCategory.class, session);
    }

    public FinancialCategory create(FinancialCategory parent, String name, User owner) {
        return update(new FinancialCategory(), parent, name, owner);
    }


    public FinancialCategory update(FinancialCategory financialCategory, FinancialCategory parent, String name, User owner) {
        financialCategory.setParent(parent);
        financialCategory.setName(name);
        financialCategory.setOwner(owner);
        return update(financialCategory);
    }

    public List<FinancialCategory> find_User(User user) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();

            var criteriaQuery = queryBuilder.getCriteriaQuery();
            var root = fetch(queryBuilder.getRoot());
            var builder = queryBuilder.getBuilder();

            var query = entityManager.createQuery(
                    criteriaQuery.where(
                            builder.or(
                                    builder.equal(root.get("owner"), user)
                            )
                    ).orderBy(builder.asc(root.get("id"))).distinct(true)
            );
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
