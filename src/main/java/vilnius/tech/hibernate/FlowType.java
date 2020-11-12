package vilnius.tech.hibernate;

public class FlowType implements BaseEntity {

    private Integer id;

    private String name;
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
