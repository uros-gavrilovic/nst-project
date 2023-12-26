package nst.springboot.restexample01.adapter.impl;

import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.adapter.DtoEntityAdapter;
import nst.springboot.restexample01.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentAdapter implements DtoEntityAdapter<DepartmentDto, Department> {
    private MemberAdapter memberAdapter;

    @Autowired
    public DepartmentAdapter(MemberAdapter memberAdapter) {
        this.memberAdapter = memberAdapter;
    }

    @Override
    public DepartmentDto toDto(Department entity) {
        DepartmentDto dto = new DepartmentDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSupervisor(memberAdapter.toDto(entity.getSupervisor()));
        dto.setSecretary(memberAdapter.toDto(entity.getSecretary()));
        entity.getMembers().forEach(member -> {
            dto.getMembers().add(memberAdapter.toDto(member));
        });

        return dto;
    }

    @Override
    public Department toEntity(DepartmentDto dto) {
        Department entity = new Department();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSupervisor(memberAdapter.toEntity(dto.getSupervisor()));
        entity.setSecretary(memberAdapter.toEntity(dto.getSecretary()));
        dto.getMembers().forEach(memberDto -> {
            entity.getMembers().add(memberAdapter.toEntity(memberDto));
        });

        return entity;
    }
    
}
