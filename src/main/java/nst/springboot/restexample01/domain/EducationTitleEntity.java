package nst.springboot.restexample01.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.springboot.restexample01.domain.enums.EducationTitle;

@Entity
@Data
@Table(name = "tbl_education_title")
@NoArgsConstructor
public class EducationTitleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "education_title")
    private EducationTitle educationTitle;

    public EducationTitleEntity(EducationTitle educationTitle) {
        this.educationTitle = educationTitle;
    }
}
