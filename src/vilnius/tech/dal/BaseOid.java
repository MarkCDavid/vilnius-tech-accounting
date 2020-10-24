package vilnius.tech.dal;

import vilnius.tech.session.Session;

import java.io.Serializable;

public abstract class BaseOid implements Serializable {

    public BaseOid(Session session, Class<? extends BaseOid> classInfo) {
        this.session = session;
        this.classInfo = classInfo;

        this.session.add(classInfo, this);
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

    private int oid;
    private final Session session;

    private final Class<? extends BaseOid> classInfo;
}
