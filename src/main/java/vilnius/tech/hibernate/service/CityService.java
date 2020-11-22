package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.City;
import vilnius.tech.hibernate.Country;

import java.util.List;

public class CityService extends HibernateService<City> {

    public CityService(Session session) {
        super(City.class, session);
    }

    public City create(String name, Country country) {
        return update(new City(), name, country);
    }

    public City update(City city, String name, Country country) {
        city.setName(name);
        city.setCountry(country);
        return update(city);
    }

    public List<City> find_Country(Country country) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();

            var query = entityManager.createQuery(queryBuilder.getCriteriaQuery().where(
                    queryBuilder.getBuilder().equal(queryBuilder.getRoot().get("country"), country)
            ));
            return query.getResultList();
        }
    }
    public List<City> find_Name(String name) {
        if(name == null || name.isBlank())
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
                                            root.get("name"),
                                            name
                                    )
                            )
                            .distinct(true)
            );

            return query.getResultList();
        }
    }

}
