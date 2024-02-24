package com.project.caseManagement.validators;

import com.project.caseManagement.dtos.CaseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CaseValidator {


    public static final String DESRIPTION_ERROR = " Veuillez renseigner la description ";
    public static final String TITLE_ERROR = " Veuillez rensigner le titre ";

    public static List<String> validate(CaseDto caseDto) {
        List<String> errors = new ArrayList<>();
        if (caseDto == null) {
            errors.add(DESRIPTION_ERROR);
            errors.add(TITLE_ERROR);
            return errors;
        }

        if (!StringUtils.hasLength(caseDto.getDescription())) {
            errors.add(DESRIPTION_ERROR);
        }

        if (!StringUtils.hasLength(caseDto.getTitle())) {
            errors.add(TITLE_ERROR);
        }
        return errors;
    }
}
