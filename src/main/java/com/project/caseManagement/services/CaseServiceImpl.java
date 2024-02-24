package com.project.caseManagement.services;

import com.project.caseManagement.dtos.CaseDto;
import com.project.caseManagement.entities.Case;
import com.project.caseManagement.exceptions.CaseEntityNotFoundException;
import com.project.caseManagement.exceptions.InvalidCaseEntityException;
import com.project.caseManagement.mappers.CaseMapper;
import com.project.caseManagement.repositories.CaseRpository;
import com.project.caseManagement.validators.CaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional

public class CaseServiceImpl implements CaseService {

    public static final String ERROR_CASE_VALIDATION = " Erreur de validation du case : ";
    public static final String CASE_NOT_FOUND = " Case non trouvé ";


    @Autowired
    private CaseRpository caseRpository;

    @Override
    public CaseDto createCase(CaseDto caseDto) {
        List<String> erros = CaseValidator.validate(caseDto);
        if (!erros.isEmpty()) {
            throw new InvalidCaseEntityException(ERROR_CASE_VALIDATION, erros);
        }
        Case caseToSave = CaseMapper.toEntity(caseDto);
        return CaseMapper.fromEntity(caseRpository.save(caseToSave));
    }

    @Override
    public CaseDto updateCase(Long id, CaseDto caseDtoToUpdate) {

        System.out.println(caseDtoToUpdate);

        List<String> errors = CaseValidator.validate(caseDtoToUpdate);
        if (!errors.isEmpty()) {
            throw new InvalidCaseEntityException(ERROR_CASE_VALIDATION, errors);
        }

        Case aCase = caseRpository.findById(id).orElseThrow(() ->
            new CaseEntityNotFoundException(CASE_NOT_FOUND));

        aCase.setTitle(caseDtoToUpdate.getTitle());
        aCase.setDescription(caseDtoToUpdate.getDescription());


        return CaseMapper.fromEntity(caseRpository.save(aCase));
    }

    @Override
    public CaseDto findById(Long id) {
        //TODO check id
        return caseRpository.findById(id)
            .map(CaseMapper::fromEntity)
            .orElseThrow(() -> new CaseEntityNotFoundException(CASE_NOT_FOUND));
    }

    @Override
    public void deleteCase(Long id) {
        if (id != null) {
            caseRpository.findById(id).orElseThrow(() ->
                new CaseEntityNotFoundException(CASE_NOT_FOUND));
            caseRpository.deleteById(id);
        }
    }
}
