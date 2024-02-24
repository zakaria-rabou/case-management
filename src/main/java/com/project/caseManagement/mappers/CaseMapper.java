package com.project.caseManagement.mappers;

import com.project.caseManagement.dtos.CaseDto;
import com.project.caseManagement.entities.Case;
import org.springframework.beans.BeanUtils;

public class CaseMapper {

    public static CaseDto fromEntity(Case acase){
        CaseDto caseDto = new CaseDto();
        BeanUtils.copyProperties(acase, caseDto);
        return caseDto;
    }

    public static Case toEntity(CaseDto caseDto){
        Case aCase = new Case();
        BeanUtils.copyProperties(caseDto, aCase);
        return aCase;
    }


}
