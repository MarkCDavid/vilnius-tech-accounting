package vilnius.tech.hibernate.service;

import org.hibernate.Session;
import vilnius.tech.error.DatabaseExceptionPolicy;
import vilnius.tech.hibernate.utils.EntityManagerAutoClosable;

import javax.persistence.EntityManagerFactory;

public class CRUDService {

    private final EntityManagerFactory entityManagerFactory;

    public CRUDService(Session session) {
        this.entityManagerFactory = session.getEntityManagerFactory();
    }


    public <T> T update(T item) {
        try (var entityManager = getEntityManager()) {
            T merged = entityManager.merge(item);
            entityManager.persist(merged);
            return merged;
        }
    }

    public <T> void remove(T item) {
        try (var entityManager = getEntityManager()) {
            entityManager.remove(entityManager.merge(item));
        }
    }

    public EntityManagerAutoClosable getEntityManager() {
        return new EntityManagerAutoClosable(entityManagerFactory.createEntityManager());
    }
}
