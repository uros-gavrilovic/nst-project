package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.domain.ScientificFieldEntity;
import nst.springboot.restexample01.domain.enums.ScientificField;
import nst.springboot.restexample01.repository.ScientificFieldEntityRepository;
import nst.springboot.restexample01.service.ScientificFieldEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScientificFieldEntityServiceImpl implements ScientificFieldEntityService {
    @Autowired
    private ScientificFieldEntityRepository scientificFieldEntityRepository;

    @Override
    public ScientificFieldEntity findByScientificField(String scientificField) {
        return scientificFieldEntityRepository.findByScientificField(
                ScientificField.valueOf(scientificField.toUpperCase()));
    }
}
