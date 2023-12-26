package nst.springboot.restexample01.adapter.impl;

import nst.springboot.restexample01.adapter.DtoEntityAdapter;
import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.dto.AssociationDto;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MemberAdapter implements DtoEntityAdapter<MemberDto, Member> {
    private final DepartmentAdapter departmentAdapter;

    @Autowired
    public MemberAdapter(@Lazy DepartmentAdapter departmentAdapter) {
        this.departmentAdapter = departmentAdapter;
    }

    @Override
    public MemberDto toDto(Member entity) {
        MemberDto dto = new MemberDto();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAcademicTitle(entity.getAcademicTitle());
        dto.setEducationTitle(entity.getEducationTitle());
        dto.setScientificField(entity.getScientificField());
        dto.setDepartment(departmentAdapter.toDto(entity.getDepartment()));

        return dto;
    }

    @Override
    public Member toEntity(MemberDto dto) {
        Member entity = new Member();

        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAcademicTitle(dto.getAcademicTitle());
        entity.setEducationTitle(dto.getEducationTitle());
        entity.setScientificField(dto.getScientificField());
        entity.setDepartment(departmentAdapter.toEntity(dto.getDepartment()));

        return entity;
    }
}
