package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.adapter.impl.MemberAdapter;
import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.domain.audit.MemberAudit;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.repository.MemberRepository;
import nst.springboot.restexample01.repository.audit.MemberAuditRepository;
import nst.springboot.restexample01.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    private void logMemberAudit(Member member, String fieldName, String oldValue, String newValue) {
        MemberAudit audit = new MemberAudit();

        audit.setEntityId(member.getId());
        audit.setField(fieldName);
        audit.setOldValue(oldValue);
        audit.setNewValue(newValue);

        memberAuditRepository.save(audit);
    }
}
