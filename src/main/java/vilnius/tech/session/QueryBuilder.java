package vilnius.tech.session;

import org.hibernate.Session;
import vilnius.tech.utils.ReflectionUtils;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

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
