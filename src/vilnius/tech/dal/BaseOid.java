package vilnius.tech.dal;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;

public abstract class BaseOid implements Serializable {

    public BaseOid(Session session) {
        this.session = session;
        this.session.add(getClass(), this);
    }

    public Session getSession() {
        return session;
    }

    public abstract String toShortString();

    public <V extends BaseOid> void setValue(V value, String valueFieldName, String referenceFieldName) {
        try {
            Field valueField = getClass().getDeclaredField(valueFieldName);
            Field referenceField = value.getClass().getDeclaredField(referenceFieldName);

            valueField.setAccessible(true);
            referenceField.setAccessible(true);

            if (valueField.get(this) != null) {
                //noinspection unchecked
                List<BaseOid> references = (List<BaseOid>) referenceField.get(valueField.get(this));
                references.remove(this);
            }

            valueField.set(this, value);

            if (valueField.get(this) != null) {
                //noinspection unchecked
                List<BaseOid> references = (List<BaseOid>) referenceField.get(valueField.get(this));
                references.add(this);
            }
        }
        catch (NoSuchFieldException | IllegalAccessException exception) {
            throw new InvalidBaseOidAccess();
        }
    }


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

    private int oid;
    private boolean deleted;

    private final Session session;
}
