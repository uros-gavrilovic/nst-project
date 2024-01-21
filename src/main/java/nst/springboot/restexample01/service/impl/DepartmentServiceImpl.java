/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import nst.springboot.restexample01.adapter.impl.MemberAdapter;
import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.domain.audit.DepartmentAudit;
import nst.springboot.restexample01.domain.enums.DepartmentPosition;
import nst.springboot.restexample01.domain.network.NetworkPackage;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.repository.DepartmentRepository;
import nst.springboot.restexample01.repository.MemberRepository;
import nst.springboot.restexample01.repository.audit.DepartmentAuditRepository;
import nst.springboot.restexample01.service.DepartmentService;
import nst.springboot.restexample01.adapter.impl.DepartmentAdapter;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.exception.DepartmentAlreadyExistException;
import nst.springboot.restexample01.util.constants.DepartmentFields;
import org.springframework.stereotype.Service;

/**
 * @author student2
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentAdapter departmentAdapter;
    private final MemberAdapter memberAdapter;
    private final DepartmentRepository departmentRepository;
    private final DepartmentAuditRepository departmentAuditRepository;
    private final MemberRepository memberRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentAdapter departmentAdapter,
                                 DepartmentAuditRepository departmentAuditRepository, MemberAdapter memberAdapter,
                                 MemberRepository memberRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentAdapter = departmentAdapter;
        this.departmentAuditRepository = departmentAuditRepository;
        this.memberAdapter = memberAdapter;
        this.memberRepository = memberRepository;
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
        throw new UnsupportedOperationException(
                "Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        return departmentRepository.findAll()
                .stream()
                .map(entity -> departmentAdapter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto promote(Long departmentId, String position, NetworkPackage networkPackage) {
        switch (DepartmentPosition.valueOf(position.toUpperCase())) {
            case SUPERVISOR:
                return updateSupervisor(departmentId, networkPackage);
            case SECRETARY:
                return updateSecretary(departmentId, networkPackage);
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    public DepartmentDto updateSupervisor(Long departmentId, NetworkPackage<MemberDto> networkPackage) {
        Member newSupervisor = memberRepository.findById(networkPackage.getData().getId())
                .orElseThrow(() -> new RuntimeException("Member does not exist!"));

        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();

            Member oldSupervisor = department.getSupervisor();

            department.setSupervisor(newSupervisor);

            logDepartmentAudit(department, "supervisor", oldSupervisor, newSupervisor, networkPackage.getDateTime());
            return departmentAdapter.toDto(departmentRepository.save(department));
        } else {
            throw new RuntimeException("Department does not exist!");
        }
    }

    public DepartmentDto updateSecretary(Long departmentId, NetworkPackage<MemberDto> networkPackage) {
        Member newSecretary = memberRepository.findById(networkPackage.getData().getId())
                .orElseThrow(() -> new RuntimeException("Member does not exist!"));

        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();

            Member oldSecretary = department.getSecretary();

            department.setSecretary(newSecretary);

            logDepartmentAudit(department, "secretary", oldSecretary, newSecretary, networkPackage.getDateTime());
            return departmentAdapter.toDto(departmentRepository.save(department));
        } else {
            throw new RuntimeException("Department does not exist!");
        }
    }

    @Override
    public List<DepartmentAudit> getHistory(Long id, String field) {
        try {
            String fieldName = DepartmentFields.valueOf(field.toUpperCase()).getFieldName().toLowerCase();
            return departmentAuditRepository.findByEntityIdAndFieldOrderByRevDateTimeDesc(id, fieldName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid field: " + field);
        }
    }

    private void logDepartmentAudit(Department department,
                                    String fieldName,
                                    Member oldValue,
                                    Member newValue,
                                    LocalDateTime dateTime) {
        DepartmentAudit audit = new DepartmentAudit();

        audit.setEntityId(department.getId());
        audit.setField(fieldName);
        audit.setOldValue(getIdAsString(oldValue));
        audit.setNewValue(getIdAsString(newValue));
        audit.setRevDateTime(dateTime == null ? LocalDateTime.now() : dateTime);

        departmentAuditRepository.save(audit);
    }

    private String getIdAsString(Member member) {
        return Optional.ofNullable(member).map(Member::getId).map(Object::toString).orElse(null);
    }
}
