package nst.springboot.restexample01.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_member")
@EntityListeners(AuditingEntityListener.class)
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "academic_title_id")
    private AcademicTitleEntity academicTitleEntity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "education_title_id")
    private EducationTitleEntity educationTitleEntity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "scientific_field_id")
    private ScientificFieldEntity scientificFieldEntity;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
