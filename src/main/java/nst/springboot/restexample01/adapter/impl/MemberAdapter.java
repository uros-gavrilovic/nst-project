package nst.springboot.restexample01.adapter.impl;

import nst.springboot.restexample01.adapter.DtoEntityAdapter;
import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberAdapter implements DtoEntityAdapter<MemberDto, Member> {
    @Autowired
    private DepartmentAdapter departmentAdapter;

    @Override
    public MemberDto toDto(Member entity) {
        return new MemberDto(
                entity.getId(),
                entity.getFirstName() + " " + entity.getLastName(),
                entity.getAcademicTitle(),
                entity.getEducationTitle(),
                entity.getScientificField(),
                departmentAdapter.toDto(entity.getDepartment())
        );
    }

    @Override
    public Member toEntity(MemberDto dto) {
        // TODO
        String firstName = dto.getFullName().split(" ")[0];
        String lastName = dto.getFullName().split(" ")[1];

        return new Member(
                dto.getId(),
                firstName,
                lastName,
                dto.getAcademicTitle(),
                dto.getEducationTitle(),
                dto.getScientificField(),
                departmentAdapter.toEntity(dto.getDepartment()));
    }
}
