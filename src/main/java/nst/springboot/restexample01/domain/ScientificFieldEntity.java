package nst.springboot.restexample01.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import nst.springboot.restexample01.domain.enums.ScientificField;

@Entity
@Data
@Table(name = "tbl_scientific_field")
@NoArgsConstructor
public class ScientificFieldEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "scientific_field")
    private ScientificField scientificField;

    public ScientificFieldEntity(ScientificField scientificField) {
        this.scientificField = scientificField;
    }
}
