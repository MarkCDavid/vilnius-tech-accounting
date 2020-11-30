package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.BaseEntity;
import vilnius.tech.hibernate.utils.EntityManagerAutoClosable;
import vilnius.tech.hibernate.utils.QueryBuilder;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public abstract class HibernateService<T extends BaseEntity> implements AutoCloseable {

    private final EntityManagerFactory entityManagerFactory;

    public HibernateService(Class<T> type, Session session) {
        this.type = type;
        this.session = session;
        this.entityManagerFactory = session.getEntityManagerFactory();
    }

    public T update(T item) {
        try (var entityManager = getEntityManager()) {
            T merged = entityManager.merge(item);
            entityManager.persist(merged);
            return merged;
        }
    }

    public void remove(T item) {
        try (var entityManager = getEntityManager()) {
            entityManager.remove(entityManager.merge(item));
        }
    }

    public T find(T item) {
        return find(item.getId());
    }

    public T find(Integer id) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();

            var criteriaQuery = queryBuilder.getCriteriaQuery();
            var root = queryBuilder.getRoot();
            var builder = queryBuilder.getBuilder();

            var query = entityManager.createQuery(
                    criteriaQuery.where(
                            builder.equal(root.get("id"), id)
                    ).distinct(true)
            );

            var results = query.getResultList();
            return results.size() == 1 ? results.get(0) : null;
        }
    }

    public List<T> find() {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();

            var criteriaQuery = queryBuilder.getCriteriaQuery();
            var root = queryBuilder.getRoot();
            var builder = queryBuilder.getBuilder();

            var query = entityManager.createQuery(
                criteriaQuery.distinct(true)
            );

            return query.getResultList();
        }
    }

    public List<T> find(int take, int skip) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();

            var criteriaQuery = queryBuilder.getCriteriaQuery();
            var root = queryBuilder.getRoot();
            var builder = queryBuilder.getBuilder();

            var query = entityManager.createQuery(
                    criteriaQuery.distinct(true)
            );

            query.setMaxResults(take);
            query.setFirstResult(skip);

            return query.getResultList();
        }
    }

    protected EntityManagerAutoClosable getEntityManager() {
        return new EntityManagerAutoClosable(entityManagerFactory);
    }


    protected QueryBuilder<T> constructQueryBuilder() {
        return new QueryBuilder<>(getType(), getSession());
    }

    protected Session getSession() {
        return session;
    }

    public Class<T> getType() {
        return type;
    }

    public void close() {
        session.disconnect();
    }

    private final Class<T> type;
    private final Session session;
}

