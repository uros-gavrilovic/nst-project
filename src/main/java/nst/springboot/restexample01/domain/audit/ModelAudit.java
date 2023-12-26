package nst.springboot.restexample01.domain.audit;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;

@Data
@MappedSuperclass
public abstract class ModelAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long entityId; // Reference to the original entity id

    private String field;

    private String oldValue;

    private String newValue;

    private Long rev;

    private int revType;
}
