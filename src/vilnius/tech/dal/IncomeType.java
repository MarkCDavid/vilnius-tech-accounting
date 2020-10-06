package vilnius.tech.dal;

import java.io.Serializable;

public class IncomeType extends BaseOid implements Serializable {

    public IncomeType(Session session) {
        super(session);
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
    public String toShortString() {
        return null;
    }
}
