package nst.springboot.restexample01.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_association",
       uniqueConstraints = @UniqueConstraint(columnNames = {"department_id", "member_id"}))
public class Association {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}