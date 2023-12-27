package nst.springboot.restexample01.domain;

import jakarta.persistence.*;
import lombok.Data;
import nst.springboot.restexample01.domain.enums.AcademicTitle;

@Entity
@Data
@Table(name = "tbl_academic_title")
public class AcademicTitleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "academic_title")
    private AcademicTitle academicTitle;
}
