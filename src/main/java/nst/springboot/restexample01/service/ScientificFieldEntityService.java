package nst.springboot.restexample01.service;

import nst.springboot.restexample01.domain.ScientificFieldEntity;

public interface ScientificFieldEntityService {
    ScientificFieldEntity findByScientificField(String scientificField);
}
