package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.hibernate.BaseEntity;
import vilnius.tech.session.EntityManagerAutoClosable;
import vilnius.tech.session.QueryBuilder;

import javax.persistence.EntityManagerFactory;
import java.util.Set;

public class SetService<O extends BaseEntity, T extends BaseEntity> {

    public SetService(O owner, Set<T> set, Session session) {
        this.owner = owner;
        this.set = set;
        this.session = session;
        this.entityManagerFactory = session.getEntityManagerFactory();
    }

    public void add(T item) {
        try (var entityManager = getEntityManager()) {
            owner = entityManager.merge(owner);
            set.add(entityManager.merge(item));
            entityManager.persist(owner);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(T item) {
        try (var entityManager = getEntityManager()) {
            owner = entityManager.merge(owner);
            set.remove(entityManager.merge(item));
            entityManager.persist(owner);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected EntityManagerAutoClosable getEntityManager() {
        return new EntityManagerAutoClosable(entityManagerFactory.createEntityManager());
    }
    protected Session getSession() {
        return session;
    }

    private final Session session;
    private final EntityManagerFactory entityManagerFactory;


    private O owner;
    private Set<T> set;
}

