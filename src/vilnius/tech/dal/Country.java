package vilnius.tech.dal;

import java.io.Serializable;

public class Country extends BaseOid implements Serializable {

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

    @Override
    public String toShortString() {
        return null;
    }
}
