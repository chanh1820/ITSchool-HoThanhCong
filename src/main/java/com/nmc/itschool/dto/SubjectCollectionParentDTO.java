package com.nmc.itschool.dto;

import com.nmc.itschool.entity.SubjectCollectionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubjectCollectionParentDTO {

    private Long id;

    private String subjectCollectionParentCode;

    private String subjectCollectionParentName;

    private String prefix;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Boolean deleteFlag;

    private List<SubjectCollectionEntity> subjectCollectionEntities;
}

