package com.project.caseManagement.validators;

import com.project.caseManagement.dtos.CaseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CaseValidatorTest {

    @Test
    void should_return_error_without_description() {

        CaseDto caseDto = new CaseDto();
        caseDto.setTitle("Case Titre");

        List<String> errors = CaseValidator.validate(caseDto);
        assertThat(errors).containsExactly(CaseValidator.DESRIPTION_ERROR);

    }

    @Test
    void should_return_error_without_title() {

        CaseDto caseDto = new CaseDto();
        caseDto.setDescription("Case Description");

        List<String> errors = CaseValidator.validate(caseDto);

        assertThat(errors).containsExactly(CaseValidator.TITLE_ERROR);


    }

    @Test
    void should_return_ok() {

        CaseDto caseDto = new CaseDto();
        caseDto.setTitle("Caase Titre");
        caseDto.setDescription("Caase Description");

        List<String> errros = CaseValidator.validate(caseDto);

        assertThat(errros).isEmpty();


    }

    @Test
    void should_return_ko() {

        CaseDto caseDto = null;

        List<String> errros = CaseValidator.validate(caseDto);

        assertThat(errros).containsExactlyInAnyOrder(CaseValidator.TITLE_ERROR, CaseValidator.DESRIPTION_ERROR);

    }

}
