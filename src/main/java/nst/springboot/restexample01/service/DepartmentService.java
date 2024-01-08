/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package nst.springboot.restexample01.service;

import java.util.List;

import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.domain.audit.DepartmentAudit;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.MemberDto;

/**
 *
 * @author student2
 */
public interface DepartmentService {
    DepartmentDto save(DepartmentDto departmentDto) throws Exception;
    List<DepartmentDto> getAll();
    void delete(Long id) throws Exception;
    void update(DepartmentDto department) throws Exception;
    DepartmentDto findById(Long id) throws Exception;

    DepartmentDto updateSupervisor(Long id, MemberDto supervisor);
    DepartmentDto updateSecretary(Long id, MemberDto secretary);
    List<DepartmentAudit> getHistory(Long id, String field);
}
