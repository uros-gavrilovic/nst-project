package nst.springboot.restexample01.adapter.impl;

import nst.springboot.restexample01.adapter.DtoEntityAdapter;
import nst.springboot.restexample01.domain.Association;
import nst.springboot.restexample01.dto.AssociationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class AssociationAdapter implements DtoEntityAdapter<AssociationDto, Association> {
    private final MemberAdapter memberAdapter;
    private final DepartmentAdapter departmentAdapter;

    @Autowired
    public AssociationAdapter(@Lazy MemberAdapter memberAdapter, @Lazy DepartmentAdapter departmentAdapter) {
        this.memberAdapter = memberAdapter;
        this.departmentAdapter = departmentAdapter;
    }

    @Override
    public AssociationDto toDto(Association entity) {
        AssociationDto dto = new AssociationDto();

        dto.setId(entity.getId());
        dto.setMemberDto(memberAdapter.toDto(entity.getMember()));
        dto.setDepartmentDto(departmentAdapter.toDto(entity.getDepartment()));

        return dto;
    }

    @Override
    public Association toEntity(AssociationDto dto) {
        Association entity = new Association();

        entity.setId(dto.getId());
        entity.setMember(memberAdapter.toEntity(dto.getMemberDto()));
        entity.setDepartment(departmentAdapter.toEntity(dto.getDepartmentDto()));

        return entity;
    }
}
