package com.project.caseManagement.web;

import com.project.caseManagement.dtos.CaseDto;
import com.project.caseManagement.exceptions.CaseEntityNotFoundException;
import com.project.caseManagement.exceptions.InvalidCaseEntityException;
import com.project.caseManagement.services.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CaseController {

    @Autowired
    private CaseService CaseService;


    @PostMapping("/Cases")
    public ResponseEntity createCase(@RequestBody CaseDto CaseDto) {
        ResponseEntity response;
        try {
            CaseDto aCase = CaseService.createCase(CaseDto);
            response = ResponseEntity.ok(aCase);
        } catch (InvalidCaseEntityException e) {
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }


    @PutMapping("Cases/{idCase}")
    public ResponseEntity updateCase(@PathVariable(name = "idCase") Long id, @RequestBody CaseDto caseDtoToUpdate) {
        ResponseEntity response;
        try {
            CaseDto caseDto = CaseService.updateCase(id, caseDtoToUpdate);
            response = ResponseEntity.ok(caseDto);
        } catch (CaseEntityNotFoundException e) {
            response = ResponseEntity.notFound().build();
        } catch (InvalidCaseEntityException e) {
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @GetMapping("/Cases/{idCase}")
    public ResponseEntity findById(@PathVariable(name = "idCase") Long id) {
        ResponseEntity response;
        try {
            CaseDto caseDto = CaseService.findById(id);
            response = ResponseEntity.ok(caseDto);
        } catch (CaseEntityNotFoundException e) {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @DeleteMapping("/Cases/{idCase}")
    public ResponseEntity deleteCase(@PathVariable(name = "idCase") Long id) {
        ResponseEntity response;
        try {
            CaseService.deleteCase(id);
            response = ResponseEntity.ok().build();
        } catch (CaseEntityNotFoundException e) {
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
}
