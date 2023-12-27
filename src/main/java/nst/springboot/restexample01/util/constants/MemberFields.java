package nst.springboot.restexample01.util.constants;

public enum MemberFields {
    ACADEMIC_TITLE("academic_title"),
    EDUCATION_TITLE("education_title"),
    SCIENTIFIC_FIELD("scientific_field");

    private final String fieldName;

    MemberFields(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return this.fieldName;
    }
}
