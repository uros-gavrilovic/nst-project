package nst.springboot.restexample01.domain;

import jakarta.persistence.*;
import lombok.Data;
import nst.springboot.restexample01.domain.enums.ScientificField;

@Entity
@Data
@Table(name = "tbl_scientific_field")
public class ScientificFieldEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "scientific_field")
    private ScientificField scientificField;
}
