package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.*;

import javax.persistence.criteria.Root;
import java.sql.Timestamp;
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

    @Override
    public Root<Expense> fetch(Root<Expense> root) {
        root.fetch("category");
        root.fetch("owner");
        root.fetch("type");
        return root;
    }

    public List<Expense> find_Category(FinancialCategory category) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = getQueryBuilder().begin();

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
