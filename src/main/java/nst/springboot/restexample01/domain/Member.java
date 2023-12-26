package nst.springboot.restexample01.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.springboot.restexample01.domain.enums.AcademicTitle;
import nst.springboot.restexample01.domain.enums.EducationTitle;
import nst.springboot.restexample01.domain.enums.ScientificField;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "academic_title_id")
    private AcademicTitle academicTitle;

    @NotEmpty
    @Column(name = "education_title_id")
    private EducationTitle educationTitle;

    @NotEmpty
    @Column(name = "scientific_field_id")
    private ScientificField scientificField;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
