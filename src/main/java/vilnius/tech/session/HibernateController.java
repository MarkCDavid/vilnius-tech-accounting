package vilnius.tech.session;

import org.hibernate.Session;
import vilnius.tech.hibernate.BaseEntity;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public abstract class HibernateController<T extends BaseEntity> {

    public HibernateController(Class<T> type, Session session) {
        this.type = type;
        this.session = session;
        this.entityManagerFactory = session.getEntityManagerFactory();
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

    public List<T> find() {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = getQueryBuilder();
            var query = entityManager.createQuery(queryBuilder.begin().getCriteriaQuery());

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> find(int take, int skip) {
        try (var entityManager = getEntityManager()) {
            var queryBuilder = getQueryBuilder();
            var query = entityManager.createQuery(queryBuilder.begin().getCriteriaQuery());

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

    protected QueryBuilder<T> getQueryBuilder() {
        return new QueryBuilder<>(getType(), getSession());
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
}

