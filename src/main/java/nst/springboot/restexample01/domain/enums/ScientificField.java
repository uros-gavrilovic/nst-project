package nst.springboot.restexample01.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScientificField  {
    MATHEMATICS,
    PHYSICS,
    COMPUTER_SCIENCE,
    HISTORY,
    OTHER;
}