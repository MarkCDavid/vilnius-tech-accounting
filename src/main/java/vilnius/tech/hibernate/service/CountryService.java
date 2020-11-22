package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.Country;

import java.util.List;

public class CountryService extends HibernateService<Country> {

    public CountryService(Session session) {
        super(Country.class, session);
    }

    public Country create(String name, String code) {
        var country = new Country();
        country.setName(name);
        country.setCode(code);
        return update(country);
    }

    public List<Country> find_Code(String code) {
        if(code == null || code.isBlank())
            return find();

        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();

            var criteriaQuery = queryBuilder.getCriteriaQuery();
            var root = queryBuilder.getRoot();
            var builder = queryBuilder.getBuilder();

            var query = entityManager.createQuery(
                    criteriaQuery
                            .where(
                                    builder.equal(
                                            root.get("code"),
                                            code
                                    )
                            )
                            .distinct(true)
            );

            return query.getResultList();
        }
    }



}
