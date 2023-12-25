package nst.springboot.restexample01.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AcademicTitle implements AbstractEnum {
    PROFESSOR("PROFESSOR"),
    ASSOCIATE_PROFESSOR("ASSOCIATE_PROFESSOR"),
    ASSISTANT_PROFESSOR("ASSISTANT_PROFESSOR");

    private final String value;
}