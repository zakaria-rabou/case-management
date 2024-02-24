package com.project.caseManagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaseDto {

    private Long caseId;

    private String title;

    private String description;


}
