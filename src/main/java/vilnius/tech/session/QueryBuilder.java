package vilnius.tech.session;

import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class QueryBuilder<T> {

    public QueryBuilder(Class<T> type, Session session) {
        this.session = session;
        this.type = type;
    }

    public QueryBuilder<T> begin() {
        criteriaBuilder = session.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(type);
        root = criteriaQuery.from(type);
        criteriaQuery = criteriaQuery.select(root);
        return this;
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

    private final Session session;
    private final Class<T> type;

    private CriteriaBuilder criteriaBuilder;

    private CriteriaQuery<T> criteriaQuery;
    private Root<T> root;
}
