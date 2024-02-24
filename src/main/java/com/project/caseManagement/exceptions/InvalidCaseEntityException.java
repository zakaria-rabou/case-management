package com.project.caseManagement.exceptions;

import java.util.List;

public class InvalidCaseEntityException extends RuntimeException{

    private List<String> errors;

    public InvalidCaseEntityException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public InvalidCaseEntityException(String message) {
        super(message);
    }


}
