package nst.springboot.restexample01.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import nst.springboot.restexample01.domain.enums.AcademicTitle;
import nst.springboot.restexample01.domain.enums.EducationTitle;

@Data
@Entity
@Table(name = "tbl_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name = "academic_title")
    private AcademicTitle academicTitle;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(name = "education_title")
    private EducationTitle educationTitle;

    @NotEmpty
    @Column(name = "scientific_field")
    private String scientificField;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
