package com.project.caseManagement.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditedEntity implements Serializable {



    @CreatedDate
    @Column(name = "creationDate", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(name = "lastUpdateDate")
    private LocalDateTime lastUpdateDate;
}
