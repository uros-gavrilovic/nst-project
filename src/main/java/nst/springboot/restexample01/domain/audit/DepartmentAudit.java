package nst.springboot.restexample01.domain.audit;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_department_audit")
public class DepartmentAudit extends ModelAudit{}
