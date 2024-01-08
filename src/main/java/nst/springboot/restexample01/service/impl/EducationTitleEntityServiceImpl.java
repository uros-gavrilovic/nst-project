package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.domain.EducationTitleEntity;
import nst.springboot.restexample01.domain.enums.EducationTitle;
import nst.springboot.restexample01.repository.EducationTitleEntityRepository;
import nst.springboot.restexample01.service.EducationTitleEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationTitleEntityServiceImpl implements EducationTitleEntityService {
    @Autowired
    private EducationTitleEntityRepository educationTitleEntityRepository;

    @Override
    public EducationTitleEntity findByEducationTitle(String educationTitle) {
        return educationTitleEntityRepository.findByEducationTitle(
                EducationTitle.valueOf(educationTitle.toUpperCase()));
    }
}
