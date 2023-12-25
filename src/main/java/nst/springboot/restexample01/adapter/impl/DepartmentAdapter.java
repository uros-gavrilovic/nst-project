package nst.springboot.restexample01.adapter.impl;

import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.adapter.DtoEntityAdapter;
import nst.springboot.restexample01.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentAdapter implements DtoEntityAdapter<DepartmentDto, Department> {
    @Autowired
    private AssignmentAdapter assignmentAdapter;
    @Autowired
    private AssociationAdapter associationAdapter;

    @Override
    public DepartmentDto toDto(Department entity) {
        DepartmentDto dto = new DepartmentDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        entity.getAssignments().forEach(assignment -> {
            dto.getAssignments().add(assignmentAdapter.toDto(assignment));
        });
        entity.getAssociations().forEach(association -> {
            dto.getAssociations().add(associationAdapter.toDto(association));
        });

        return dto;
    }

    @Override
    public Department toEntity(DepartmentDto dto) {
        Department entity = new Department();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        dto.getAssignments().forEach(assignmentDto -> {
            entity.getAssignments().add(assignmentAdapter.toEntity(assignmentDto));
        });
        dto.getAssociations().forEach(associationDto -> {
            entity.getAssociations().add(associationAdapter.toEntity(associationDto));
        });

        return entity;
    }
    
}
