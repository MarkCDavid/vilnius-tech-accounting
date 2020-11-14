package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.BaseEntity;
import vilnius.tech.session.EntityManagerAutoClosable;
import vilnius.tech.session.QueryBuilder;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class HibernateService<T extends BaseEntity> {

    public HibernateService(Class<T> type, Session session) {
        this.type = type;
        this.session = session;
        this.entityManagerFactory = session.getEntityManagerFactory();
        this.toFetch = new HashSet<>();
    }

    public void fetch(String... fields) {
        Collections.addAll(toFetch, fields);
    }

    public T update(T item) {
        try (var entityManager = getEntityManager()) {
            T merged = entityManager.merge(item);
            entityManager.persist(merged);
            return merged;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void remove(T item) {
        try (var entityManager = getEntityManager()) {
            entityManager.remove(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T find(T item) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = constructQueryBuilder();

            var criteriaQuery = queryBuilder.getCriteriaQuery();
            var root = queryBuilder.getRoot();
            var builder = queryBuilder.getBuilder();

            var query = entityManager.createQuery(
                criteriaQuery.where(
                    builder.equal(root.get("id"), item.getId())
                ).distinct(true)
            );

            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected EntityManagerAutoClosable getEntityManager() {
        return new EntityManagerAutoClosable(entityManagerFactory.createEntityManager());
    }

    protected QueryBuilder<T> constructQueryBuilder() {
        var queryBuilder = new QueryBuilder<>(getType(), getSession());
        fetch(queryBuilder.getRoot());
        return queryBuilder;
    }

    protected Root<T> fetch(Root<T> root) {
        for(var field: toFetch) {
            root.fetch(field, JoinType.LEFT);
        }
        toFetch.clear();

        return root;
    }

    protected Session getSession() {
        return session;
    }

    public Class<T> getType() {
        return type;
    }

    private final Class<T> type;
    private final Session session;
    private final EntityManagerFactory entityManagerFactory;

    private final Set<String> toFetch;
}

