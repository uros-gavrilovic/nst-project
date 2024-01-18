package nst.springboot.restexample01.repository.audit;

import nst.springboot.restexample01.domain.audit.MemberAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberAuditRepository extends JpaRepository<MemberAudit, Long> {
    List<MemberAudit> findByEntityIdAndFieldOrderByRevDateTimeDesc(Long entityId, String field);

    List<MemberAudit> findByEntityIdOrderByRevDateTimeDesc(Long id);
}