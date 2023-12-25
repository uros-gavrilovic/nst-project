package nst.springboot.restexample01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDto {
    private Long id;

    private DepartmentDto departmentDto;

    private MemberDto memberDto;

    private LocalDate assignedDate;
}
