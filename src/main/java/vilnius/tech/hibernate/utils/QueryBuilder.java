package vilnius.tech.hibernate.utils;

import org.hibernate.Session;

import javax.persistence.criteria.*;

public class QueryBuilder<T> {

    public QueryBuilder(Class<T> type, Session session) {

        criteriaBuilder = session.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(type);

        root = criteriaQuery.from(type);
        criteriaQuery = criteriaQuery.select(root);
    }

    public CriteriaBuilder getBuilder() {
        return criteriaBuilder;
    }

    public Root<T> getRoot() {
        return root;
    }

    public CriteriaQuery<T> getCriteriaQuery() {
        return criteriaQuery;
    }

    private CriteriaQuery<T> criteriaQuery;

    private final CriteriaBuilder criteriaBuilder;
    private final Root<T> root;
}
