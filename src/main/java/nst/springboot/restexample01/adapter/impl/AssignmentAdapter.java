package nst.springboot.restexample01.adapter.impl;

import nst.springboot.restexample01.adapter.DtoEntityAdapter;
import nst.springboot.restexample01.domain.Assignment;
import nst.springboot.restexample01.dto.AssignmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AssignmentAdapter implements DtoEntityAdapter<AssignmentDto, Assignment> {
    private final MemberAdapter memberAdapter;
    private final DepartmentAdapter departmentAdapter;

    @Autowired
    public AssignmentAdapter(@Lazy MemberAdapter memberAdapter, @Lazy DepartmentAdapter departmentAdapter) {
        this.memberAdapter = memberAdapter;
        this.departmentAdapter = departmentAdapter;
    }

    @Override
    public AssignmentDto toDto(Assignment entity) {
        AssignmentDto dto = new AssignmentDto();

        dto.setId(entity.getId());
        dto.setMemberDto(memberAdapter.toDto(entity.getMember()));
        dto.setDepartmentDto(departmentAdapter.toDto(entity.getDepartment()));
        dto.setAssignedDate(entity.getAssignedDate());

        return dto;
    }

    @Override
    public Assignment toEntity(AssignmentDto dto) {
        Assignment entity = new Assignment();

        entity.setId(dto.getId());
        entity.setMember(memberAdapter.toEntity(dto.getMemberDto()));
        entity.setDepartment(departmentAdapter.toEntity(dto.getDepartmentDto()));
        entity.setAssignedDate(dto.getAssignedDate());

        return entity;
    }
}
