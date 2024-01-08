package nst.springboot.restexample01.service;

import nst.springboot.restexample01.domain.EducationTitleEntity;

public interface EducationTitleEntityService {
    EducationTitleEntity findByEducationTitle(String educationTitle);
}
