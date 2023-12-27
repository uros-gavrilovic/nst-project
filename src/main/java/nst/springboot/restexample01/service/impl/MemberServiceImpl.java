package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.adapter.impl.MemberAdapter;
import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.domain.audit.MemberAudit;
import nst.springboot.restexample01.domain.enums.AcademicTitle;
import nst.springboot.restexample01.domain.enums.EducationTitle;
import nst.springboot.restexample01.domain.enums.ScientificField;
import nst.springboot.restexample01.dto.DepartmentDto;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.repository.MemberRepository;
import nst.springboot.restexample01.repository.audit.MemberAuditRepository;
import nst.springboot.restexample01.service.MemberService;
import nst.springboot.restexample01.util.constants.DepartmentFields;
import nst.springboot.restexample01.util.constants.MemberFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    private Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Member does not exist!"));
    }

    @Override
    public MemberDto updateAcademicTitle(Long memberID, AcademicTitle academicTitle) {
        return updateQualifications(memberID, MemberFields.ACADEMIC_TITLE, academicTitle, Member::setAcademicTitle, Member::getAcademicTitle);
    }

    @Override
    public MemberDto updateEducationTitle(Long memberID, EducationTitle educationTitle) {
        return updateQualifications(memberID, MemberFields.EDUCATION_TITLE, educationTitle, Member::setEducationTitle, Member::getEducationTitle);
    }

    @Override
    public MemberDto updateScientificField(Long memberID, ScientificField scientificField) {
        return updateQualifications(memberID, MemberFields.SCIENTIFIC_FIELD, scientificField.to, Member::setScientificField, Member::getScientificField);
    }

    private <T extends Enum<T>> MemberDto updateQualifications(Long memberID, MemberFields fieldName, String newQualification,
                                                               BiConsumer<Member, T> setter, Function<String, T> converter) {
        Optional<Member> optionalMember = memberRepository.findById(memberID);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            T oldValue = converter.apply(member.getQualification(fieldName));
            T newValue = converter.apply(newQualification);

            setter.accept(member, newValue);

            member = memberRepository.save(member);
            logMemberAudit(member, fieldName.getFieldName(), oldValue.toString(), newValue.toString());

            return memberAdapter.toDto(member);
        } else {
            throw new RuntimeException("Member does not exist!");
        }
    }

    private void logMemberAudit(Member member, String fieldName, String oldValue, String newValue) {
        MemberAudit audit = new MemberAudit();

        audit.setEntityId(member.getId());
        audit.setField(fieldName);
        audit.setOldValue(oldValue);
        audit.setNewValue(newValue);

        memberAuditRepository.save(audit);
    }
}
