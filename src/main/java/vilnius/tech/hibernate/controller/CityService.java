package vilnius.tech.hibernate.controller;

import org.hibernate.Session;
import vilnius.tech.hibernate.City;
import vilnius.tech.hibernate.Country;

import java.util.List;

public class CityService extends HibernateService<City> {

    public CityService(Session session) {
        super(City.class, session);
    }

    public City create(String name, Country country) {
        var city = new City();
        city.setName(name);
        city.setCountry(country);
        return update(city);
    }

    public List<City> find_Country(Country country) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = getQueryBuilder().begin();

            var query = entityManager.createQuery(queryBuilder.getCriteriaQuery().where(
                    queryBuilder.getBuilder().equal(queryBuilder.getRoot().get("country"), country)
            ));
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
