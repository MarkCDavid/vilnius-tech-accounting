package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.Income;
import vilnius.tech.hibernate.IncomeType;
import vilnius.tech.hibernate.User;

import java.util.List;

public class IncomeTypeService extends HibernateService<IncomeType> {

    public IncomeTypeService(Session session) {
        super(IncomeType.class, session);
    }

    public IncomeType create(String name, String code) {
        return update(new IncomeType(), name, code);
    }

    public IncomeType update(IncomeType incomeType, String name, String code) {
        incomeType.setName(name);
        incomeType.setCode(code);
        return update(incomeType);
    }

    public IncomeType find_Code(String code) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();
            var query = entityManager.createQuery(queryBuilder.getCriteriaQuery().where(
                    queryBuilder.getBuilder().like(queryBuilder.getRoot().get("code"), code)
            ));

            var results = query.getResultList();
            if (results.isEmpty())
                return null;

            return results.get(0);
        }
    }

}
