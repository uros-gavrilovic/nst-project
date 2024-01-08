package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.adapter.impl.MemberAdapter;
import nst.springboot.restexample01.domain.*;
import nst.springboot.restexample01.domain.audit.MemberAudit;
import nst.springboot.restexample01.domain.enums.AcademicTitle;
import nst.springboot.restexample01.domain.enums.EducationTitle;
import nst.springboot.restexample01.domain.enums.QualificationType;
import nst.springboot.restexample01.domain.enums.ScientificField;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.repository.AcademicTitleEntityRepository;
import nst.springboot.restexample01.repository.EducationTitleEntityRepository;
import nst.springboot.restexample01.repository.MemberRepository;
import nst.springboot.restexample01.repository.ScientificFieldEntityRepository;
import nst.springboot.restexample01.repository.audit.MemberAuditRepository;
import nst.springboot.restexample01.service.AcademicTitleEntityService;
import nst.springboot.restexample01.service.EducationTitleEntityService;
import nst.springboot.restexample01.service.MemberService;
import nst.springboot.restexample01.service.ScientificFieldEntityService;
import nst.springboot.restexample01.util.constants.DepartmentFields;
import nst.springboot.restexample01.util.constants.MemberFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberAuditRepository memberAuditRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberAdapter memberAdapter;
    @Autowired
    private AcademicTitleEntityService academicTitleEntityService;
    @Autowired
    private EducationTitleEntityService educationTitleEntityService;
    @Autowired
    private ScientificFieldEntityService scientifitFieldEntityService;

    @Override
    public MemberDto save(MemberDto memberDto) {
        Member member = memberAdapter.toEntity(memberDto);
        memberRepository.save(member);

        return memberDto;
    }

    @Override
    public Page<MemberDto> getAll(Pageable pageable) {
        return memberRepository.findAll(pageable).map(memberAdapter::toDto);
    }

    @Override
    public void delete(Long id) {
        Member member = getMemberById(id);
        memberRepository.delete(member);
    }

    @Override
    public void update(MemberDto memberDto) {
        Member member = getMemberById(memberDto.getId());
        memberRepository.save(member);
    }

    @Override
    public MemberDto findById(Long id) {
        Member member = getMemberById(id);
        return memberAdapter.toDto(member);
    }

    @Override
    public MemberDto updateQualifications(Long id, String type, String value) {
        try {
            QualificationType qualificationType = QualificationType.valueOf(type.toUpperCase());
            value = value.toUpperCase();

            Optional<Member> optionalMember = memberRepository.findById(id);
            if (optionalMember.isEmpty()) throw new RuntimeException("Member does not exist!");
            Member member = optionalMember.get();

            String oldValue = null;
            String newValue = value;
            MemberFields fieldName = null;

            switch (qualificationType) {
                case ACADEMIC_TITLE:
                    AcademicTitleEntity academicTitleEntity = academicTitleEntityService.findByAcademicTitle(value);
                    oldValue = member.getAcademicTitleEntity().getAcademicTitle().toString();
                    fieldName = MemberFields.ACADEMIC_TITLE;
                    member.setAcademicTitleEntity(academicTitleEntity);
                    break;
                case EDUCATION_TITLE:
                    EducationTitleEntity educationTitleEntity = educationTitleEntityService.findByEducationTitle(value);
                    oldValue = member.getEducationTitleEntity().getEducationTitle().toString();
                    fieldName = MemberFields.EDUCATION_TITLE;
                    member.setEducationTitleEntity(educationTitleEntity);
                    break;
                case SCIENTIFIC_FIELD:
                    ScientificFieldEntity scientificFieldEntity =
                            scientifitFieldEntityService.findByScientificField(value);
                    oldValue = member.getScientificFieldEntity().getScientificField().toString();
                    fieldName = MemberFields.SCIENTIFIC_FIELD;
                    member.setScientificFieldEntity(scientificFieldEntity);
                    break;
            }
            member = memberRepository.save(member);
            logMemberAudit(member, fieldName, oldValue, newValue);
            return memberAdapter.toDto(member);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid qualification type/value!");
        }
    }

    private Member getMemberById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            return optionalMember.get();
        } else {
            throw new RuntimeException("Member does not exist!");
        }
    }

    private void logMemberAudit(Member member, MemberFields fieldName, String oldValue, String newValue) {
        MemberAudit audit = new MemberAudit();

        audit.setEntityId(member.getId());
        audit.setField(fieldName.toString());
        audit.setOldValue(oldValue);
        audit.setNewValue(newValue);
        audit.setRevDateTime(LocalDateTime.now());

        memberAuditRepository.save(audit);
    }
}
