package nst.springboot.restexample01.util.constants;

public enum DepartmentFields {
    SUPERVISOR("supervisor"),
    SECRETARY("secretary");

    private final String fieldName;

    DepartmentFields(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return this.fieldName;
    }
}