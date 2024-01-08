package nst.springboot.restexample01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociationDto {
    private Long id;
    private DepartmentDto departmentDto;
    private MemberDto memberDto;
}
