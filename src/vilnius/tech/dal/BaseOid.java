package vilnius.tech.dal;

import java.io.Serializable;

public abstract class BaseOid implements Serializable {

    public BaseOid(Session session) {
        this.session = session;
        this.session.add(getClass(), this);
    }

    public Session getSession() {
        return session;
    }

    public abstract String toShortString();

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    protected <T> String formatValue(T value, String caption) {
        return String.format("%s: %s%n", caption, value != null ? value.toString() : null);
    }

    protected <T> String formatReference(T reference, String caption) {
        return String.format("=== %s ===%n%s%n=== %s ===%n", caption, reference != null ? reference.toString() : null, caption);
    }

    private int oid;
    private boolean deleted;

    private final Session session;
}
