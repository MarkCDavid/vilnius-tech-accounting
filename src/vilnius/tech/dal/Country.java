package vilnius.tech.dal;

import java.io.Serializable;

public class Country extends BaseOid implements Serializable {

    public Country(Session session) {
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

    @Override
    public String toString() {
        return formatValue(name, "Country Name") + formatValue(code, "Code");
    }

    @Override
    public String toShortString() {
        return String.format("%s (%s)", name, code);
    }

    private String name;
    private String code;
}
