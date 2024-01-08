package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.domain.AcademicTitleEntity;
import nst.springboot.restexample01.domain.enums.AcademicTitle;
import nst.springboot.restexample01.repository.AcademicTitleEntityRepository;
import nst.springboot.restexample01.service.AcademicTitleEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicTitleEntityServiceImpl implements AcademicTitleEntityService {
    @Autowired
    private AcademicTitleEntityRepository academicTitleEntityRepository;

    @Override
    public AcademicTitleEntity findByAcademicTitle(String academicTitle) {
        return academicTitleEntityRepository.findByAcademicTitle(AcademicTitle.valueOf(academicTitle.toUpperCase()));
    }
}
