package vilnius.tech.dal;

import vilnius.tech.session.Session;

import java.io.Serializable;

public class IncomeType extends BaseOid implements Serializable {

    public IncomeType(Session session) {
        super(session, IncomeType.class);
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
        return String.format("%s (%s)", name, code);
    }
}
