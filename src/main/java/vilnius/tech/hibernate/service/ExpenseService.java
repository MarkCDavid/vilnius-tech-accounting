package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.*;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService extends HibernateService<Expense> {

    public ExpenseService(Session session) {
        super(Expense.class, session);
    }

    public Expense create(User owner, long sum, Timestamp timestamp, FinancialCategory category, ExpenseType type) {
        return update(new Expense(), owner, sum, timestamp, category, type);
    }

    public Expense update(Expense expense, User owner, long sum, Timestamp timestamp, FinancialCategory category, ExpenseType type) {
        expense.setOwner(owner);
        expense.setSum(sum);
        expense.setTimestamp(timestamp);
        expense.setCategory(category);
        expense.setType(type);
        return update(expense);
    }

    public List<Expense> find_Category(FinancialCategory category) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();

            var criteriaQuery = queryBuilder.getCriteriaQuery();
            var root = queryBuilder.getRoot();
            var builder = queryBuilder.getBuilder();



            var query = entityManager.createQuery(criteriaQuery.where(
                    builder.equal(root.get("category"), category)
            ).distinct(true));

            return query.getResultList();
        }
    }

    public List<Expense> find_Category(FinancialCategory category, Timestamp from, Timestamp to) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();

            var criteriaQuery = queryBuilder.getCriteriaQuery();
            var root = queryBuilder.getRoot();
            var builder = queryBuilder.getBuilder();

            var predicates = new ArrayList<Predicate>();

            predicates.add(builder.equal(root.get("category"), category));

            if(from != null)
                predicates.add(builder.greaterThanOrEqualTo(root.get("timestamp"), from));

            if(to != null)
                predicates.add(builder.lessThanOrEqualTo(root.get("timestamp"), to));

            var predicatesArray = new Predicate[predicates.size()];
            predicates.toArray(predicatesArray);

            var query = entityManager.createQuery(criteriaQuery.where(
                    builder.and(predicatesArray)
            ).distinct(true));

            return query.getResultList();
        }
    }

}
