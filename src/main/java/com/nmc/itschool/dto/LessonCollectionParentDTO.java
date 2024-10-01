package com.nmc.itschool.dto;

import com.nmc.itschool.entity.LessonCollectionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LessonCollectionParentDTO {

    private Integer id;

    private String lessonCode;

    private String lessonName;

    private String prefix;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Boolean deleteFlag;

    private List<LessonCollectionEntity> lessonCollectionEntities;
}

