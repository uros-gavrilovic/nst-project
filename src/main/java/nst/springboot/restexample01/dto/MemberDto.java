package nst.springboot.restexample01.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.springboot.restexample01.domain.enums.AcademicTitle;
import nst.springboot.restexample01.domain.enums.EducationTitle;
import nst.springboot.restexample01.domain.enums.ScientificField;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private AcademicTitle academicTitle;

    @Enumerated(EnumType.STRING)
    private EducationTitle educationTitle;

    @Enumerated(EnumType.STRING)
    private ScientificField scientificField;

    private Set<AssociationDto> departmentAssociations;
}