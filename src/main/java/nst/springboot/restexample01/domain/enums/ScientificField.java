package nst.springboot.restexample01.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScientificField implements AbstractEnum {
    MATHEMATICS("MATHEMATICS"),
    PHYSICS("PHYSICS"),
    COMPUTER_SCIENCE("COMPUTER_SCIENCE"),
    HISTORY("HISTORY"),
    OTHER("OTHER");

    private final String value;
}