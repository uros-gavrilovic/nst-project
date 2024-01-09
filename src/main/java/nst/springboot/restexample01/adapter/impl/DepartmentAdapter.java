package nst.springboot.restexample01.adapter.impl;

import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.adapter.DtoEntityAdapter;
import nst.springboot.restexample01.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DepartmentAdapter implements DtoEntityAdapter<DepartmentDto, Department> {
    private final MemberAdapter memberAdapter;

    @Autowired
    public DepartmentAdapter(MemberAdapter memberAdapter) {
        this.memberAdapter = memberAdapter;
    }

    @Override
    public DepartmentDto toDto(Department entity) {
        if (entity == null) return null;
        DepartmentDto dto = new DepartmentDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSupervisor(memberAdapter.toDto(entity.getSupervisor()));
        dto.setSecretary(memberAdapter.toDto(entity.getSecretary()));

        return dto;
    }

    @Override
    public Department toEntity(DepartmentDto dto) {
        if (dto == null) return null;
        Department entity = new Department();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSupervisor(memberAdapter.toEntity(dto.getSupervisor()));
        entity.setSecretary(memberAdapter.toEntity(dto.getSecretary()));

        return entity;
    }

}
