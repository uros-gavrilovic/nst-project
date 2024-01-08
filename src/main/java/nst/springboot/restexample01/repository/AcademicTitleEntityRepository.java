package nst.springboot.restexample01.repository;

import nst.springboot.restexample01.domain.AcademicTitleEntity;
import nst.springboot.restexample01.domain.enums.AcademicTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicTitleEntityRepository extends JpaRepository<AcademicTitleEntity, Long> {
    AcademicTitleEntity findByAcademicTitle(AcademicTitle academicTitle);
}
