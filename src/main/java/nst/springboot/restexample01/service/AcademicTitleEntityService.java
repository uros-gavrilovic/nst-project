package nst.springboot.restexample01.service;

import nst.springboot.restexample01.domain.AcademicTitleEntity;

public interface AcademicTitleEntityService {
    AcademicTitleEntity findByAcademicTitle(String academicTitle);
}
