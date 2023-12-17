/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import nst.springboot.restexample01.domain.Subject;
import nst.springboot.restexample01.repository.DepartmentRepository;
import nst.springboot.restexample01.repository.SubjectRepository;
import nst.springboot.restexample01.service.SubjectService;
import nst.springboot.restexample01.adapter.impl.DepartmentAdapter;
import nst.springboot.restexample01.adapter.impl.SubjectAdapter;
import nst.springboot.restexample01.dto.SubjectDto;
import org.springframework.stereotype.Service;

/**
 *
 * @author student2
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    private DepartmentAdapter departmentAdapter;
    private SubjectAdapter subjectAdapter;

    private SubjectRepository subjectRepository;
    private DepartmentRepository departmentRepository;

    public SubjectServiceImpl(
            SubjectRepository subjectRepository,
            DepartmentRepository departmentRepository,
            DepartmentAdapter departmentAdapter, SubjectAdapter subjectAdapter) {
        this.departmentRepository = departmentRepository;
        this.subjectRepository = subjectRepository;
        this.departmentAdapter = departmentAdapter;
        this.subjectAdapter = subjectAdapter;
    }

    @Override
    public SubjectDto save(SubjectDto subjectDto) throws Exception {
          throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SubjectDto> getAll() {
        return subjectRepository
                .findAll()
                .stream().map(entity -> subjectAdapter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            Subject subj = subject.get();
            subjectRepository.delete(subj);
        } else {
            throw new Exception("Subject does not exist!");
        }

    }

    @Override
    public void update(SubjectDto subjectDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SubjectDto findById(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            //postoji
            Subject subj = subject.get();
            return subjectAdapter.toDto(subj);
        } else {
            throw new Exception("Subject does not exist!");
        }
    }

}
