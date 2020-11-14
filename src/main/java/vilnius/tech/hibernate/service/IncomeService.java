package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.*;

import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

public class IncomeService extends HibernateService<Income> {

    public IncomeService(Session session) {
        super(Income.class, session);
    }

    public Income create(User owner, long sum, Timestamp timestamp, FinancialCategory category, IncomeType type) {
        return update(new Income(), owner, sum, timestamp, category, type);
    }
    public Income update(Income income, User owner, long sum, Timestamp timestamp, FinancialCategory category, IncomeType type) {
        income.setOwner(owner);
        income.setSum(sum);
        income.setTimestamp(timestamp);
        income.setCategory(category);
        income.setType(type);
        return update(income);
    }

    public List<Income> find_Category(FinancialCategory category) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();

            var criteriaQuery = queryBuilder.getCriteriaQuery();
            var root = fetch(queryBuilder.getRoot());
            var builder = queryBuilder.getBuilder();

            var query = entityManager.createQuery(criteriaQuery.where(
                    builder.equal(root.get("category"), category)
            ).distinct(true));

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
