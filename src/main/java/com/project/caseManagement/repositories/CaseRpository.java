package com.project.caseManagement.repositories;

import com.project.caseManagement.entities.Case;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CaseRpository extends JpaRepository<Case, Long> {



}
