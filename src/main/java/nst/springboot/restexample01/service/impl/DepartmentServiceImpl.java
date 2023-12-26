/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.domain.audit.DepartmentAudit;
import nst.springboot.restexample01.repository.DepartmentRepository;
import nst.springboot.restexample01.repository.audit.DepartmentAuditRepository;
import nst.springboot.restexample01.service.DepartmentService;
import nst.springboot.restexample01.adapter.impl.DepartmentAdapter;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.exception.DepartmentAlreadyExistException;
import org.springframework.stereotype.Service;

/**
 *
 * @author student2
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentAdapter departmentAdapter;
    private DepartmentRepository departmentRepository;
    private DepartmentAuditRepository departmentAuditRepository;

    public DepartmentServiceImpl(
            DepartmentRepository departmentRepository,
            DepartmentAdapter departmentAdapter,
            DepartmentAuditRepository departmentAuditRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentAdapter = departmentAdapter;
        this.departmentAuditRepository = departmentAuditRepository;
    }

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) throws Exception {
        Optional<Department> dept = departmentRepository.findByName(departmentDto.getName());
        if (dept.isPresent()) {
            throw new DepartmentAlreadyExistException("Department sa tim imenom postoji!");
        } else {
            //DTO - > Entity
            //Department department = new Department(departmentDto.getId(), departmentDto.getName());
            Department department = departmentAdapter.toEntity(departmentDto);
            department = departmentRepository.save(department);
            return departmentAdapter.toDto(department);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        //proveri da li postoji department
        Optional<Department> dept = departmentRepository.findById(id);
        if (dept.isPresent()) {
            //postoji
            Department department = dept.get();
            departmentRepository.delete(department);
        } else {
            throw new Exception("Department does not exist!");
        }

    }

    @Override
    public void update(DepartmentDto department) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DepartmentDto findById(Long id) throws Exception {
        Optional<Department> dept = departmentRepository.findById(id);
        if (dept.isPresent()) {
            //postoji
            Department department = dept.get();
            return departmentAdapter.toDto(department);
        } else {
            throw new Exception("Department does not exist!");
        }
    }

    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository
                .findAll()
                .stream().map(entity -> departmentAdapter.toDto(entity))
                .collect(Collectors.toList());
    }

    private void logDepartmentAudit(Department department, String fieldName, Member oldValue, Member newValue){
        DepartmentAudit audit = new DepartmentAudit();

        audit.setEntityId(department.getId());
        audit.setField(fieldName);
        audit.setOldValue(Long.toString(oldValue.getId()));
        audit.setNewValue(Long.toString(newValue.getId()));

        departmentAuditRepository.save(audit);
    }

}
