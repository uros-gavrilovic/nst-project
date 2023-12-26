package nst.springboot.restexample01.repository.audit;

import nst.springboot.restexample01.domain.audit.DepartmentAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DepartmentAuditRepository extends JpaRepository<DepartmentAudit, Long> {

    List<DepartmentAudit> findByEntityIdAndFieldOrderByRevDesc(Long entityId, String field);
}