/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.adapter.impl;

import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.adapter.DtoEntityAdapter;
import nst.springboot.restexample01.dto.DepartmentDto;
import org.springframework.stereotype.Component;

/**
 *
 * @author student2
 */

@Component
public class DepartmentAdapter implements DtoEntityAdapter<DepartmentDto, Department> {

    @Override
    public DepartmentDto toDto(Department entity) {
        return new DepartmentDto(entity.getId(), entity.getName());
    }

    @Override
    public Department toEntity(DepartmentDto dto) {
        return new Department(dto.getId(), dto.getName());
    }
    
}
