package com.nmc.itschool.dto;

import com.nmc.itschool.entity.SubjectCollectionParentEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SubjectCollectionDTO {

    private Long id;

    private String subjectCollectionCode;

    private String subjectCollectionName;

    private String prefix;

    private Integer sortOrder;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Boolean deleteFlag;

    private SubjectCollectionParentEntity subjectCollectionParentEntity;
}

