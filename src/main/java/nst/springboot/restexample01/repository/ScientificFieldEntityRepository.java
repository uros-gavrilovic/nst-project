package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.ScientificFieldEntity;
import nst.springboot.restexample01.domain.enums.ScientificField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScientificFieldEntityRepository extends JpaRepository<ScientificFieldEntity, Long> {
    ScientificFieldEntity findByScientificField(ScientificField scientificField);
}
