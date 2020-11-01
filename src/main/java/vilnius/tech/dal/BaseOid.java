package vilnius.tech.dal;

import vilnius.tech.session.Session;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


public abstract class BaseOid implements Serializable {


    public BaseOid(Session session, Class<? extends BaseOid> classInfo) {
        this.session = session;
        this.classInfo = classInfo;
        this.session.add(this);
    }

    public Session getSession() {
        return session;
    }

    public Class<? extends BaseOid> getClassInfo() {
        return classInfo;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void delete() {
        this.session.delete(this);
    }

    private int oid;

    private final Session session;

    private final Class<? extends BaseOid> classInfo;
}
