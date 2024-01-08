package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.EducationTitleEntity;
import nst.springboot.restexample01.domain.enums.EducationTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationTitleEntityRepository extends JpaRepository<EducationTitleEntity, Long> {
    EducationTitleEntity findByEducationTitle(EducationTitle educationTitle);
}
