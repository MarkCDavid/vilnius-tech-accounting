package vilnius.tech.dal;


import java.io.Serializable;

public abstract class BaseOid implements Serializable {

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

    private int oid;
    private boolean deleted;
}
