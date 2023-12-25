package nst.springboot.restexample01.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EducationTitle implements AbstractEnum {
    BACHELOR("BACHELOR"),
    MASTER("MASTER"),
    DOCTOR("DOCTOR");

    private final String value;
}
