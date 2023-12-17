/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.adapter.impl;

import nst.springboot.restexample01.domain.Subject;
import nst.springboot.restexample01.adapter.DtoEntityAdapter;
import nst.springboot.restexample01.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author student2
 */

@Component
public class SubjectAdapter implements DtoEntityAdapter<SubjectDto, Subject> {
    @Autowired
    private DepartmentAdapter departmentAdapter;
    
    @Override
    public SubjectDto toDto(Subject entity) {
        return new SubjectDto(
                entity.getId(), 
                entity.getName(), entity.getEsbp(), 
                departmentAdapter.toDto(entity.getDepartment())
        );
    }

    @Override
    public Subject toEntity(SubjectDto dto) {
        return new Subject(
                dto.getId(), 
                dto.getName(), 
                dto.getEsbp(),
        departmentAdapter.toEntity(dto.getDepartmentDto()));
    }
    
}
