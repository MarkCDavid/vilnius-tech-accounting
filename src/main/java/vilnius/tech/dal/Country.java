package vilnius.tech.dal;

import vilnius.tech.session.Session;

import javax.persistence.Entity;
import java.io.Serializable;

public class Country extends BaseOid implements Serializable {

    public Country(Session session) {
        super(session, Country.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String name;
    private String code;

    @Override
    public String toString() {
        return getName();
    }
}
