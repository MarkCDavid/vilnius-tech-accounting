package vilnius.tech.hibernate;

import com.google.gson.annotations.Expose;

public class FlowType implements BaseEntity {

    @Expose
    private Integer id;

    @Expose
    private String name;

    @Expose
    private String code;

    public FlowType() {

    }

    public FlowType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return String.format("%s - %s", name, code);
    }
}
