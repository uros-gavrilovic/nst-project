package nst.springboot.restexample01.adapter.impl;

import nst.springboot.restexample01.adapter.DtoEntityAdapter;
import nst.springboot.restexample01.domain.AcademicTitleEntity;
import nst.springboot.restexample01.domain.EducationTitleEntity;
import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.domain.ScientificFieldEntity;
import nst.springboot.restexample01.domain.enums.AcademicTitle;
import nst.springboot.restexample01.domain.enums.EducationTitle;
import nst.springboot.restexample01.domain.enums.ScientificField;
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
        if(entity == null) return null;
        MemberDto dto = new MemberDto();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAcademicTitle(entity.getAcademicTitleEntity().getAcademicTitle());
        dto.setEducationTitle(entity.getEducationTitleEntity().getEducationTitle());
        dto.setScientificField(entity.getScientificFieldEntity().getScientificField());
        dto.setDepartment(departmentAdapter.toDto(entity.getDepartment()));

        System.out.println("ovde je ok");
        return dto;
    }

    @Override
    public Member toEntity(MemberDto dto) {
        if(dto == null) return null;
        Member entity = new Member();

        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());

        AcademicTitleEntity academicTitleEntity = new AcademicTitleEntity();
        academicTitleEntity.setAcademicTitle(AcademicTitle.valueOf(dto.getAcademicTitle().toString()));
        entity.setAcademicTitleEntity(academicTitleEntity);

        EducationTitleEntity educationTitleEntity = new EducationTitleEntity();
        educationTitleEntity.setEducationTitle(EducationTitle.valueOf(dto.getEducationTitle().toString()));
        entity.setEducationTitleEntity(educationTitleEntity);

        ScientificFieldEntity scientificFieldEntity = new ScientificFieldEntity();
        scientificFieldEntity.setScientificField(ScientificField.valueOf(dto.getScientificField().toString()));
        entity.setScientificFieldEntity(scientificFieldEntity);

        entity.setDepartment(departmentAdapter.toEntity(dto.getDepartment()));

        return entity;
    }
}
