package com.project.caseManagement.services;

import com.project.caseManagement.dtos.CaseDto;

public interface CaseService {

    CaseDto createCase(CaseDto CaseDto);

    CaseDto updateCase(Long id, CaseDto CaseDtoToUpdate);

    CaseDto findById(Long id);

    void deleteCase(Long id);


}
