package com.nmc.itschool.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nmc.itschool.entity.LessonCollectionParentEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LessonCollectionDTO {

    private Integer id;

    private String lessonCollectionCode;

    private String lessonCollectionName;

    private String prefix;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Boolean deleteFlag;

    private LessonCollectionParentEntity lessonCollectionParentEntity;
}

