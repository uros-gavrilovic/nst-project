package nst.springboot.restexample01.controller;

import nst.springboot.restexample01.domain.Member;
import nst.springboot.restexample01.domain.enums.AcademicTitle;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<Page<MemberDto>> getAllMembers(@RequestParam int pageSize, @RequestParam String orderType) {
        Pageable pageable = PageRequest.of(0, pageSize, Sort.by(orderType));
        return new ResponseEntity<>(memberService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) throws Exception {
        MemberDto memberDto = memberService.findById(id);
        return new ResponseEntity<>(memberService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) throws Exception {
        return new ResponseEntity<>(memberService.save(memberDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable Long id, @RequestBody MemberDto memberDto) throws Exception {
        memberService.update(memberDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) throws Exception {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/update-qualifications")
    public ResponseEntity<MemberDto> updateQualifications(@PathVariable Long id,
                                                     @RequestParam(required = true) String qualificationType,
                                                     @RequestBody String newQualification) throws Exception {
        MemberDto updatedMemberDto = memberService.updateQualifications(id, qualificationType, newQualification);
        return new ResponseEntity<>(updatedMemberDto, HttpStatus.OK);
    }
}