package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface MemberService {
    MemberDto save(MemberDto memberDto) throws Exception;
    Page<MemberDto> getAll(Pageable pageable);
    void delete(Long id) throws Exception;
    void update(MemberDto memberDto) throws Exception;
    MemberDto findById(Long id) throws Exception;
}
