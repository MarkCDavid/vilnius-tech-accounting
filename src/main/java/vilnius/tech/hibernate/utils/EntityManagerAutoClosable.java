package vilnius.tech.hibernate.utils;

import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

public class EntityManagerAutoClosable implements EntityManager, AutoCloseable {

    public EntityManagerAutoClosable(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityManager.getTransaction().begin();
    }

    @Override
    public void close() {
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void persist(Object o) {
        this.entityManager.persist(o);
    }

    @Override
    public <T> T merge(T t) {
        return this.entityManager.merge(t);
    }

    @Override
    public void remove(Object o) {
        this.entityManager.remove(o);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o) {
        return this.entityManager.find(aClass, o);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, Map<String, Object> map) {
        return this.entityManager.find(aClass, o, map);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType) {
        return this.entityManager.find(aClass, o, lockModeType);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType, Map<String, Object> map) {
        return this.entityManager.find(aClass, o, lockModeType, map);
    }

    @Override
    public <T> T getReference(Class<T> aClass, Object o) {
        return this.entityManager.getReference(aClass, o);
    }

    @Override
    public void flush() {
        this.entityManager.flush();
    }

    @Override
    public void setFlushMode(FlushModeType flushModeType) {
        this.entityManager.setFlushMode(flushModeType);
    }

    @Override
    public FlushModeType getFlushMode() {
        return this.entityManager.getFlushMode();
    }

    @Override
    public void lock(Object o, LockModeType lockModeType) {
        this.entityManager.lock(o, lockModeType);
    }

    @Override
    public void lock(Object o, LockModeType lockModeType, Map<String, Object> map) {
        this.entityManager.lock(o, lockModeType, map);
    }

    @Override
    public void refresh(Object o) {
        this.entityManager.refresh(0);
    }

    @Override
    public void refresh(Object o, Map<String, Object> map) {
        this.entityManager.refresh(0, map);
    }

    @Override
    public void refresh(Object o, LockModeType lockModeType) {
        this.entityManager.refresh(0, lockModeType);
    }

    @Override
    public void refresh(Object o, LockModeType lockModeType, Map<String, Object> map) {
        this.entityManager.refresh(0, lockModeType, map);
    }

    @Override
    public void clear() {
        this.entityManager.clear();
    }

    @Override
    public void detach(Object o) {
        this.entityManager.detach(o);
    }

    @Override
    public boolean contains(Object o) {
        return this.entityManager.contains(o);
    }

    @Override
    public LockModeType getLockMode(Object o) {
        return this.entityManager.getLockMode(o);
    }

    @Override
    public void setProperty(String s, Object o) {
        this.entityManager.setProperty(s, o);
    }

    @Override
    public Map<String, Object> getProperties() {
        return this.entityManager.getProperties();
    }

    @Override
    public Query createQuery(String s) {
        return this.entityManager.createQuery(s);
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return this.entityManager.createQuery(criteriaQuery);
    }

    @Override
    public Query createQuery(CriteriaUpdate criteriaUpdate) {
        return this.entityManager.createQuery(criteriaUpdate);
    }

    @Override
    public Query createQuery(CriteriaDelete criteriaDelete) {
        return this.entityManager.createQuery(criteriaDelete);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String s, Class<T> aClass) {
        return this.entityManager.createQuery(s, aClass);
    }

    @Override
    public Query createNamedQuery(String s) {
        return this.entityManager.createNamedQuery(s);
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String s, Class<T> aClass) {
        return this.entityManager.createNamedQuery(s, aClass);
    }

    @Override
    public Query createNativeQuery(String s) {
        return this.entityManager.createNativeQuery(s);
    }

    @Override
    public Query createNativeQuery(String s, Class aClass) {
        return this.entityManager.createNativeQuery(s, aClass);
    }

    @Override
    public Query createNativeQuery(String s, String s1) {
        return this.entityManager.createNativeQuery(s, s1);
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String s) {
        return this.entityManager.createNamedStoredProcedureQuery(s);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s) {
        return this.entityManager.createStoredProcedureQuery(s);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s, Class... classes) {
        return this.entityManager.createStoredProcedureQuery(s, classes);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s, String... strings) {
        return this.entityManager.createStoredProcedureQuery(s, strings);
    }

    @Override
    public void joinTransaction() {
        this.entityManager.joinTransaction();
    }

    @Override
    public boolean isJoinedToTransaction() {
        return this.entityManager.isJoinedToTransaction();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return this.entityManager.unwrap(aClass);
    }

    @Override
    public Object getDelegate() {
        return this.entityManager.getDelegate();
    }

    @Override
    public boolean isOpen() {
        return this.entityManager.isOpen();
    }

    @Override
    public EntityTransaction getTransaction() {
        return this.entityManager.getTransaction();
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return this.entityManager.getEntityManagerFactory();
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return this.entityManager.getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel() {
        return this.entityManager.getMetamodel();
    }

    @Override
    public <T> EntityGraph<T> createEntityGraph(Class<T> aClass) {
        return this.entityManager.createEntityGraph(aClass);
    }

    @Override
    public EntityGraph<?> createEntityGraph(String s) {
        return this.entityManager.createEntityGraph(s);
    }

    @Override
    public EntityGraph<?> getEntityGraph(String s) {
        return this.entityManager.getEntityGraph(s);
    }

    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> aClass) {
        return this.entityManager.getEntityGraphs(aClass);
    }

    private final EntityManager entityManager;
}


